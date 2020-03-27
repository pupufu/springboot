package com.ppf.springboot.service.com.ppf.service.impl;

import com.ppf.springboot.entity.User;
import com.ppf.springboot.mapper.UserMapper;
import com.ppf.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
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


}
