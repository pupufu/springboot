package com.ppf.springboot.SpringBootTest;

import com.ppf.springboot.SpringbootApplication;
import com.ppf.springboot.thread.ThreadService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.CompletableFuture;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringBootAsynTest {

    @Autowired
    ThreadService threadService;



    @Test
    public void test1(){
        Long start = System.currentTimeMillis();
        CompletableFuture<String> future = threadService.foo1();
        threadService.foo2();
        String s = threadService.foo3();
        System.out.println(s);
        CompletableFuture.allOf(future).join();


        Long end = System.currentTimeMillis();
        System.out.println("执行时间："+(end-start)+"ms");
    }

}
