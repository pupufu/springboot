package com.ppf.springboot.aspect;

import com.ppf.springboot.controller.UserController;
import com.ppf.springboot.entity.User;
import com.ppf.springboot.service.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserAspect {

    @Autowired
    UserService userService;
    @Autowired
    UserController userController;

    @After("execution(* com.ppf.springboot.service.impl.UserServiceImpl.getUserList())")
    public void say() {
        System.out.println("你怎么老是偷看我啊。。。。。");
    }


    @Around("execution(* com.ppf.springboot.service.impl.UserServiceImpl.getUsername(*))")
    public User say1(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        if (args != null && args.length > 0 && args[0].getClass() == String.class) {
            System.out.println("张三不准查，给你看看张三三");
            args[0] = "张三三";
        }
        //用改变后的参数执行目标方法
      //  Object returnValue = point.proceed(args);
        User returnValue = (User) point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        System.out.println("@Around：被织入的目标对象为：" + point.getTarget());


        System.out.println("原返回值：" + returnValue + "，这是返回结果的后缀");
        return returnValue;
    }

    @Before("execution(* com.ppf.springboot.service.impl.UserServiceImpl.getUsername(*))")
    public void say2() {
        System.out.println("你要找谁啊");

    }
}
