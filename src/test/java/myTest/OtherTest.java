package myTest;


import org.apache.jasper.tagplugins.jstl.core.Out;

import java.util.ArrayList;
import java.util.List;

public class OtherTest {


    public static void main(String[] args) throws Exception {


        Runnable t1 = new Runnable() {
            @Override
            public void run() {
                ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();
                List<String> list = getList();
                threadLocal.set(getList());
                list.add("李四");
            }
        };
        Runnable t2 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//
                ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();
                List<String> list = getList();
                threadLocal.set(getList());
                threadLocal.get();
                list.add("王五");
            }
        };
        Runnable t3 = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//
                ThreadLocal<List<String>> foo = OtherTest.foo();
                List<String> list = foo.get();
                list.add("vvv");
                System.out.println("线程3："+list);
            }
        };


        Runnable t4 = ()->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ThreadLocal<List<String>> threadLocal = new ThreadLocal<>();
            List<String> list = getList();
            threadLocal.set(list);
            list.add("赵六");
        };

        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
    //    Thread.sleep(500);


        List<String> list = getList();

        for (String s : list) {
            System.out.println(s);
        }

        System.out.println(foo().get());


    }


    public static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("张三");
        return list;
    }


    public static ThreadLocal<List<String>> foo(){
        ThreadLocal<List<String>> threadLocal = new ThreadLocal<List<String>>();
        List<String> list = new ArrayList<>();
        list.add("ppp");
        threadLocal.set(list);
        return threadLocal;
    }

}
