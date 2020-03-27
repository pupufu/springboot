package com.ppf.springboot.thread;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class ThreadService {

    @Async("taskExecutor")
    public CompletableFuture<String> foo1() {

        try {
            Thread.sleep(3000);
            System.out.println("----" + Thread.currentThread().getName() + "----hello");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = "----" + Thread.currentThread().getName() + "----hello ";
        return CompletableFuture.completedFuture(s);
    }


    @Async
    public void foo2() {

        try {
            Thread.sleep(2000);
            System.out.println("----" + Thread.currentThread().getName() + "----world!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Async
    public String foo3(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String s = "welcome~~~~~~~~~~";
        System.out.println("----" + Thread.currentThread().getName() + s);
        return s;
    }

}
