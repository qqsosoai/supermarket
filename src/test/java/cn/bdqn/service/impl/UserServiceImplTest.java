package cn.bdqn.service.impl;

import cn.bdqn.bean.User;
import cn.bdqn.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.rmi.runtime.Log;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hasee on 2017/8/10.
 */
public class UserServiceImplTest {
    private ApplicationContext ac;
    private UserService service;
    private Logger log=Logger.getLogger(UserServiceImplTest.class);
    @Before
    public void set(){
        ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        service= (UserService) ac.getBean("userService");
    }
    @Test
    public void addService() throws Exception {
        User user=new User("qqsosoai1", "张丽丽", "qqsosoai1", 1, new Date(), "13333333333", "地球", 3, 2);
        boolean flag = service.addService(user);
        assertTrue(flag);
    }

    @Test
    public void updateService() throws Exception {
        User user=new User(4, "修改张丽", 1, new Date(), "11111111111", "银河系", 2);
        boolean flag = service.updateService(user);
        assertTrue(flag);
    }

    @Test
    public void delService() throws Exception {
        User user=new User();
        user.setUserId(4);
        boolean flag = service.delService(user);
        assertTrue(flag);
    }

    @Test
    public void findByNameLimit() throws Exception {
        List<User> users = service.findByNameLimit("李", 1, 3);
        assertEquals(1,users.size());
    }

    @Test
    public void findByNameSqlCount() throws Exception {
        Integer count = service.findByNameSqlCount(null);
        assertEquals(3,count.intValue());
    }

    @Test
    public void findById() throws Exception {
        User user = service.findById(1);
        log.debug(user);
    }

}