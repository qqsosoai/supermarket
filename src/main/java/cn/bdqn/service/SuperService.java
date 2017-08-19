package cn.bdqn.service;

/**
 * Created by hasee on 2017/8/10.
 */
public interface SuperService<T> {
    /**
     * 添加方法
     * @param t 泛型
     * @return 添加是否成功
     */
    boolean addService(T t)throws Exception;

    /**
     *  修改方法
     * @param t 泛型
     * @return 修改是否成功
     */
    boolean updateService(T t)throws Exception;

    /**
     *  删除方法
     * @param t 泛型
     * @return 删除是否成功
     */
    boolean delService(T t)throws Exception;
}
