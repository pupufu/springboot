package com.ppf.springboot.service.impl;

import org.springframework.stereotype.Service;

@Service
public class ThreadServiceImpl {


    public void foo1(){
        try {
            Thread.sleep(5000);
            System.out.println("foo1-----当前线程： "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void foo2(){
        try {
            Thread.sleep(3000);
            System.out.println("foo2-----当前线程： "+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
