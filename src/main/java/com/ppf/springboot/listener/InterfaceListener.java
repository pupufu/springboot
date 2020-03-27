package com.ppf.springboot.listener;

import com.ppf.springboot.annotation.MyListener;
import org.springframework.web.method.HandlerMethod;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public  class InterfaceListener extends HandlerInterceptorAdapter {



//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 如果不是映射到方法直接通过
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//        // ①:START 方法注解级拦截器
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        Annotation annotation = method.getAnnotation(MyListener.class);
//        if (null!=annotation){
//            DataMessage dataMessage = new DataMessage();
//            dataMessage.sendMessage(method);
//        }
//
//
//        return true;
//    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 如果不是映射到方法直接通过
        if (handler instanceof HandlerMethod) {
            // ①:START 方法注解级拦截器
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            Annotation annotation = method.getAnnotation(MyListener.class);
            if (null!=annotation){
                DataMessage dataMessage = new DataMessage();
                dataMessage.sendMessage(method);
            }
        }
    }
}
