package com.ppf.springboot.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.ppf.springboot.annotation.MyListener;
import com.ppf.springboot.entity.User;
import com.ppf.springboot.mapper.UserMapper;
import com.ppf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {//123

    //测试git上传123
    @Autowired
    UserService userService;


    @PostMapping("/addUser")
    @ResponseBody
    public Object addUser(){
        System.out.println("我进来了~~~~~~~~~~~~~~~~~~");
//        System.out.println("获取用户名称：" + user.getName());
//        System.out.println("获取用户年龄：" + user.getAge());
        return "666666666";
    }
//    @MyListener
//    @PostMapping("/addUser")
//    public Object addUser(User user) {
//        System.out.println("我进来了~~~~~~~~~~~~~~~~~~");
//        System.out.println("获取用户名称：" + user.getName());
//        System.out.println("获取用户年龄：" + user.getAge());
//        System.out.println("获取用户生日：" + user.getBirthday());
//        HashMap<Object, Object> map = new HashMap<>();
//        userService.addUser(user);
//        map.put("message", "sucess");
//        return map;
//    }

//    @PostMapping("/addUser")
//    public Object addUser(@RequestParam("name") String name,@RequestParam("age")String age) {
//        System.out.println("你真的秀啊！！！！！！");
//        return "你成功了！";
//    }



    @GetMapping("/userlist")
    @ResponseBody
    public List<User> getUserList() {

        List<User> userList = userService.getUserList();
        System.out.println(userList);
        return userList;
    }

    @ResponseBody
    @RequestMapping("/456")
    public String test(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        System.out.println("456中获得的username：" + username);
        return username;
    }

}