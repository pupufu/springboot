package com.ppf.springboot.listener;

import com.ppf.springboot.entity.User;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class DataMessage {


    private static ConcurrentHashMap<Method, Long> map = new ConcurrentHashMap<Method, Long>();
//    private static DataMessage dataMessage = new DataMessage();
//
//    private DataMessage() {
//
//    }
//
//    public static DataMessage getDataMessage() {
//        return dataMessage;
//    }

    public long getMessage(Method method) {
        if (map.containsKey(method)) {
            return map.get(method);
        } else {
            return -1;
        }
    }


    public void sendMessage(Method method) {


            if (!map.containsKey(method)) {
                map.put(method, 1L);
            } else {
                synchronized (DataMessage.class) {
                Long num = map.get(method);
                num++;
                map.put(method, num);

            }
        }
    }
}
