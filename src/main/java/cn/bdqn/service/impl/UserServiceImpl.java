package cn.bdqn.service.impl;

import cn.bdqn.bean.User;
import cn.bdqn.dao.UserMapper;
import cn.bdqn.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hasee on 2017/8/10.
 * 用户service层
 */
public class UserServiceImpl implements UserService {
    @Resource(name="userDao")
    private UserMapper dao;
    public boolean addService(User user) throws Exception {
        boolean flag=false;
        Integer count = dao.addUser(user);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public boolean updateService(User user) throws Exception {
        boolean flag=false;
        Integer count = dao.updateUser(user);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public boolean delService(User user) throws Exception {
        boolean flag=false;
        Integer count = dao.delUser(user);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public List<User> findByNameLimit(String name, Integer pageIndex, Integer pageSize) throws Exception {
        if (pageIndex!=null){
            pageIndex=(pageIndex-1)*pageSize;
        }
        return dao.findByLimitName(name,pageIndex,pageSize);
    }

    public Integer findByNameSqlCount(String name) throws Exception {
        return dao.findByNameSqlCount(name);
    }

    public User findById(Integer id) throws Exception {
        return dao.findById(id);
    }

    public User findByName(String username) {
        return dao.findByName(username);
    }


}
