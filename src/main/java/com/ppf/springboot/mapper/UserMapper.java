package com.ppf.springboot.mapper;

import com.ppf.springboot.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();


    List<User> findUserByNameAndAge(User user);

    List<User> findUserByName(String name);

    void addUser(User user);
}
