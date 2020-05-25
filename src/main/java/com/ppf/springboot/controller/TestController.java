package com.ppf.springboot.controller;

import com.ppf.springboot.entity.User;
import com.ppf.springboot.untis.ioUntils.IOUntil;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
@RequestMapping("/test")
public class TestController {

//    @RequestMapping("")
//    public String getPage() {
//        return "test";
//    }

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {

        File file = new File("F:\\java\\myproject\\springboot\\src\\main\\static\\file\\05入门程序(查询)_.flv");
        String fileName = file.getName();
        InputStream inputStream = new FileInputStream(file);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        IOUntil.download(file, inputStream, outputStream);


    }

    @RequestMapping("/123")
    @ResponseBody
    public Object test(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String id = session.getId();
        System.out.println("session_id = " + id);

        //     session.setMaxInactiveInterval(2);

        if (StringUtils.isEmpty(session.getAttribute("username"))) {
            session.setAttribute("username", "ppf");
            String username = (String) session.getAttribute("username");
            //   session.setMaxInactiveInterval(2);
            System.out.println("创建成功");
        } else {
            System.out.println("username已经创建了。。。。");
        }


        Cookie cookie = new Cookie("ppf", "456");
        cookie.setMaxAge(24 * 24 * 60 * 60);
        response.addCookie(cookie);
        return "添加cookie";
    }

    @GetMapping("/json")
    @ResponseBody
    public Object json(@RequestBody User user) {
        return user;
    }

}
