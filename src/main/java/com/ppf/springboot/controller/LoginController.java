package com.ppf.springboot.controller;

import com.ppf.springboot.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping("login")
    public Object longin(HttpServletRequest request, HttpServletResponse response, User user,String yzm){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("ppf","123");
        cookie.setMaxAge(7*24*60*60);
        response.addCookie(cookie);
//1112233333444
        return null;
    }

}
