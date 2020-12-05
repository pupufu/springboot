package com.ppf.springboot.service;

import com.ppf.springboot.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    List<User> getUserList();


    List<User> findUserByNameAndAge(User user);

    List<User> findUserByName(String name);

    void addUser(User user);

    List<User> queryByParams(int currentPage, int pageSize, User user);

    User getUsername(String name);

    List<User> findUserByAge(Integer age);
}
