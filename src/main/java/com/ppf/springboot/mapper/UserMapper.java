package com.ppf.springboot.mapper;

import com.ppf.springboot.entity.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> findAll();


    List<User> findUserByNameAndAge(@Param("user") User user);

    List<User> findUserByName(String name);

    void addUser(User user);

    List<User> queryByParams(User user);

    User getUsername(String name);

    List<User> findUserByAge(Integer age);
}
