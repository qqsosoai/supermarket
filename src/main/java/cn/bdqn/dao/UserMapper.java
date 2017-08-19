package cn.bdqn.dao;

import cn.bdqn.bean.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hasee on 2017/8/9.
 * 用户接口
 */
public interface UserMapper {
    /**
     * 根据用户名分页查询
     * @param userName 用户名(模糊查询)
     * @param pageIndex 当前页码
     * @param pageSize 页面大小
     * @return 用户集合
     */
    List<User> findByLimitName(@Param("userName") String userName,
                               @Param("pageIndex") Integer pageIndex,
                               @Param("pageSize") Integer pageSize);

    /**
     * 根据用户名查询数据库总记录数
     * @param userName 用户名(模糊查询)
     * @return 返回总记录数
     */
    Integer findByNameSqlCount(@Param("userName") String userName)throws Exception;

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户
     */
    User findById(Integer id)throws Exception;

    /**
     * 添加用户
     * @param user 用户
     * @return 返回影响行数
     */
    Integer addUser(User user)throws Exception;

    /**
     * 更新用户
     * @param user 用户
     * @return 返回影响行数
     */
    Integer updateUser(User user)throws Exception;

    /**
     * 删除用户(默认根据ID)
     * @param user 用户
     * @return 返回影响行数
     */
    Integer delUser(User user)throws Exception;

    /**
     * 根据用户名查找用户
     * @param username 用户名
     * @return 返回用户对象
     */
    User findByName(String username);
}
