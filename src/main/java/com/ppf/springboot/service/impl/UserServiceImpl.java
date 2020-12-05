package com.ppf.springboot.service.impl;

import com.ppf.springboot.entity.User;
import com.ppf.springboot.mapper.UserMapper;
import com.ppf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getUserList() {
        return userMapper.findAll();
    }

    @Override
    public List<User> findUserByNameAndAge(User user) {
        return userMapper.findUserByNameAndAge(user);
    }

    @Override
    public List<User> findUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public List<User> findUserByAge(Integer age) {
        return userMapper.findUserByAge(age);
    }

    @Override
    public User getUsername(String name) {
        return userMapper.getUsername(name);
    }

    @Override
    public List<User> queryByParams(int currentPage, int pageSize, User user) {


        List<User> userList = userMapper.queryByParams(user);
        List<User> subList = userList.subList((currentPage - 1) * pageSize, currentPage * pageSize);
        return subList;

    }
}
