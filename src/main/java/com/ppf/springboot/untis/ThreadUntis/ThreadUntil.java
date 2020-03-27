package com.ppf.springboot.untis.ThreadUntis;

import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.*;


public class ThreadUntil {

    private static int poolSize = 10;
    private static Map<Thread,Future<?>> map = new ConcurrentHashMap<>();
    private static ExecutorService executorService = null;

    static {
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    // @SuppressWarnings("unchecked")
    // public static <T> T execute(Object object, Method method) throws
    // InterruptedException, ExecutionException {
    // Callable<T> callable = getCallable(object, method);
    // Future<T> future = executorService.submit(callable);
    // Object result = future.get();
    // return (T) result;
    // }

    public static Object execute(Method method) throws InterruptedException, ExecutionException {


//        Future<T> future = (Future<T>) executorService.submit(callable);


        Callable<Object> callable = getCallable(method);
        setMap(callable);
        Future<?> future = map.get(Thread.currentThread());
        Object result = future.get();

        return result;



    }

    private static  <T> void setMap(Callable<T> callable){
        Future<T> future = (Future<T>) executorService.submit(callable);
        map.put(Thread.currentThread(),future);
    }

    private static  <T> void getCurrentFuture(Method method){

    }
    private static <T> List<Future<?>> getFutureList(Method method){
        List<Future<?>> futureList = new ArrayList<>();

        for (Thread thread : map.keySet()) {
            Future<?> future = map.get(thread);
            futureList.add(future);
        }
        return futureList;
    }




    @SuppressWarnings("unchecked")
    private static <T> Callable<T> getCallable(Method method) {
        return (Callable<T>) new Callable<Object>() {

            @Override
            public T call() throws Exception {
                Class<?> clazz = method.getDeclaringClass();
                Object exeObject = clazz.newInstance();
                Class<T> returnType = (Class<T>) method.getReturnType();
                Object obj = method.invoke(exeObject);
                return returnType.cast(obj);

            }
        };
    }


}
