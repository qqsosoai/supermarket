package cn.bdqn.service;

import cn.bdqn.bean.User;

import java.util.List;

/**
 * Created by hasee on 2017/8/10.
 */
public interface UserService extends SuperService<User> {
    /**
     * 根据用户姓名分页查询
     * @param name 用户姓名
     * @param pageIndex 当前页码
     * @param pageSize 页面大小
     * @return
     */
    List<User> findByNameLimit(String name,Integer pageIndex,Integer pageSize)throws Exception;

    /**
     * 根据用户姓名分页查询
     * @param name 用户姓名
     * @return 总记录数
     */
    Integer findByNameSqlCount(String name)throws Exception;

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 返回用户
     */
    User findById(Integer id)throws Exception;

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User findByName(String username);

    boolean updatePassword(Integer id,String password)throws Exception;
}
