package com.ppf.springboot.controller;

import com.ppf.springboot.entity.User;
import com.ppf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/person")
public class PersonController {


    @RequestMapping
    public String getURL() {
        return "ajax";
    }


    @Autowired
    private UserService userService;

    @ResponseBody
    @GetMapping("query")
    public List<User> findUserByNameAndAge(User user) {
        return userService.findUserByNameAndAge(user);

    }

    @ResponseBody
    @GetMapping("query/{name}")
    public List<User> findUserByName(@PathVariable("name") String name) {
        return userService.findUserByName(name);

    }
}
