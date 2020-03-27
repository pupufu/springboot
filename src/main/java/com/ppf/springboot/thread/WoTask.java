package com.ppf.springboot.thread;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class WoTask {
	// 线程池大小 默认为10
	private int pooSize = 10;
	// 任务执行器
	private ExecutorService executorService = Executors.newFixedThreadPool(pooSize);
	// 线程的任务列表
	private List<Future<Object>> tasks = new ArrayList<>();
	// 线程执行的结果集 （ key：任务（future）--- value：future.get()得到的结果）
	private Map<Future<Object>, Object> resultMap = new ConcurrentHashMap<>();

	public Future<Object> submit(Method method, Object... objects) {
		Callable<Object> callable = getCallable(method, objects);
		Future<Object> future = this.executorService.submit(callable);
		tasks.add(future);
		return future;
	}

	public void execute() throws InterruptedException, ExecutionException {

		for (Future<Object> future : tasks) {
			long start = System.currentTimeMillis();
			Object object = future.get();
			if (object != null) {
				resultMap.put(future, object);
				long end = System.currentTimeMillis();
				System.out.println(
						"get()后--->线程名字：" + Thread.currentThread().getName() + "---" + "---->>>执行时间：" + (end - start));
			} else {
				resultMap.put(future, "执行成功，无返回值");
			}

		}
	}

	public Object getResult(Future<Object> future) {
		return resultMap.get(future);
	}

	public Callable<Object> getCallable(Method method, Object... objects) {
		return new Callable<Object>() {

			@Override
			public Object call() throws Exception {
				Class<?> clazz = method.getDeclaringClass();
				Object object = clazz.newInstance();
				Class<?> returnType = method.getReturnType();
				Object result = method.invoke(object, objects);
				return returnType.cast(result);
			}
		};
	}

	public void close() {
		executorService.shutdown();
	}

	public void setPoolSize(int poolSize) {
		this.pooSize = poolSize;
	}

}
