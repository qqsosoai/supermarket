package cn.bdqn.service;

import cn.bdqn.bean.Provider;

import java.util.List;

/**
 * Created by hasee on 2017/8/10.
 */
public interface ProviderService extends SuperService<Provider>{
    /**
     * 根据供应商名称分页查询
     * @param proName 供应商名称
     * @param pageIndex 当前页码
     * @param pageSize 页面大小
     * @return 返回供应商集合
     */
    List<Provider> findByNameLimit(String proName,Integer pageIndex,Integer pageSize)throws Exception;

    /**
     * 根据供应商名称查询总数
     * @param proName 供应商名称
     * @return 返回总记录数
     */
    Integer findByNameSqlCount(String proName)throws Exception;

    /**
     * 查询供应商ID与名称给账单页面使用
     * @return 供应商集合
     */
    List<Provider> findByProviderToBill()throws  Exception;

    /**
     * 根据ID查询供应商
     * @param id 供应商ID
     * @return 返回供应商详情
     */
    Provider findById(Integer id)throws Exception;

    /**
     * 根据ID删除供应商，当供应商下面由账单则不删除
     * @param id 供应商ID
     * @return 删除是否成功
     */
    boolean deleteByBilldel(Integer id)throws Exception;
}
