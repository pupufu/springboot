package com.ppf.springboot.service;

import com.ppf.springboot.entity.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();


    List<User> findUserByNameAndAge(User user);

    List<User> findUserByName(String name);

    void addUser(User user);
}
