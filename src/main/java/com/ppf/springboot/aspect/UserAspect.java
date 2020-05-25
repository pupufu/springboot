package com.ppf.springboot.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    @After("execution(* com.ppf.springboot.service.impl.UserServiceImpl.getUserList())")
    public void say(){
        System.out.println("你怎么老是偷看我啊。。。。。");
    }


}
