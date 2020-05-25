package com.ppf.springboot.config;

import com.ppf.springboot.annotation.MyListener;
import com.ppf.springboot.listener.InterfaceListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interfaceListener());
    }
    //
//    @Bean
//    public CommonsMultipartResolver multipartFile() {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//
//        resolver.setMaxUploadSize(8 * 1024 * 1024);
//
//        return resolver;
//    }
//
//
//
//    public ServletListenerRegistrationBean listenerRegist() {
//        ServletListenerRegistrationBean srb = new ServletListenerRegistrationBean();
//        srb.setListener(new MyHttpSessionListener());
//        System.out.println("listener");
//        return srb;
//    }

    @Bean
    public InterfaceListener interfaceListener() {
        return new InterfaceListener();
    }


}
