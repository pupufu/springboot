package com.ppf.springboot.controller;

import com.ppf.springboot.entity.User;
import com.ppf.springboot.listener.DataMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@RestController
public class MessageController {


    @GetMapping("/message/user")
    public Object getMessage() {

        DataMessage DataMessage = new DataMessage();
        Method method = null;
        try {
            method = UserController.class.getMethod("getUserList");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        long message = DataMessage.getMessage(method);
        return message;

    }


}
