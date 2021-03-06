package com.ppf.springboot.controller;

import com.ppf.springboot.service.impl.ThreadServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/thread")
public class ThreadController {

    //111
    @Autowired
    ThreadServiceImpl threadServiceImpl;

    @RequestMapping("/test")
    public Object test(){
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程--->"+Thread.currentThread().getName());

        long end = System.currentTimeMillis();
        System.out.println("执行时间：  "+(end-start)+"毫秒");
        return "执行时间：  "+(end-start)+"毫秒";
    }


}
