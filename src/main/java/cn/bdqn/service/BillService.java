package cn.bdqn.service;

import cn.bdqn.bean.Bill;

import java.util.List;

/**
 * Created by hasee on 2017/8/10.
 *
 */
public interface BillService extends SuperService<Bill> {
    /**
     * 根据条件分页查询
     * @param name 账单名
     * @param proId 供应商ID
     * @param isPay 是否付款
     * @param pageIndex 当前页码
     * @param pageSize 页面大小
     * @return Bill集合
     */
    List<Bill>  findByLimit(String name,Integer proId,Integer isPay,Integer pageIndex,Integer pageSize)throws Exception;

    /**
     * 根据条件查询数据库记录数
     * @param name 账单名
     * @return 总记录数
     */
    Integer findBySqlCount(String name,Integer proId,Integer isPay)throws Exception;

    /**
     * 根据ID查询账单详情
     * @param id 账单ID
     * @return 账单详情
     */
    Bill findById(Integer id)throws Exception;

}
