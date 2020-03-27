package com.ppf.springboot.SpringBootTest;

import com.ppf.springboot.SpringbootApplication;
import com.ppf.springboot.entity.User;
import com.ppf.springboot.mapper.UserMapper;
import com.ppf.springboot.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(value = SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringBootTest1 {

    @Autowired
    UserService userService;


//        <!--事务控制-->
//    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
//        <!--控制数据源-->
//        <property name="dataSource" ref="pooledDataSource"/>
//    </bean>
    @Before
    public void before(){


    }

    @Test
    @Rollback(value = true)
    public void testDAO(){
        User user = new User();
        user.setName("ppf");
        user.setAge(18);
        userService.addUser(user);
        System.out.println(user.toString());
    }


}
