package cn.bdqn.dao;

import cn.bdqn.bean.Bill;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by hasee on 2017/8/8.
 * 账单的接口
 */
public interface BillMapper {
    /**
     * 按条件查询并分页
     * @param billName 账单名称(模糊查询)
     * @param providerId 供应商id
     * @param isPay 是否付款
     * @param pageIndex 当前页码
     * @param pageSize 页大小
     * @return 账单集合
     */
    List<Bill> findByLimitBillName(@Param("billName") String billName,@Param("proId") Integer providerId,
                                     @Param("isPay") Integer isPay,@Param("pageIndex") Integer pageIndex,
                                     @Param("pageSize") Integer pageSize)throws Exception;

    /**
     * 根据ID查询具体账单
     * @param id 账单ID
     * @return 账单明细
     */
    Bill findById(@Param("id") Integer id)throws Exception;

    /**
     * 根据条件查询数据库总记录数
     * @param billName 账单名称(模糊查询)
     * @param providerId 供应商ID
     * @param isPay 是否付款0:未支付,1:已支付
     * @return 数据库总记录数
     * @throws Exception 回滚事务
     */
    Integer findBySqlCount(@Param("billName") String billName,@Param("proId") Integer providerId,
                           @Param("isPay") Integer isPay)throws Exception;

    /**
     * 添加账单方法
     * @param bill 账单
     * @return 返回影响行数
     */
    Integer addByBill(Bill bill)throws Exception;

    /**
     * 删除账单
     * @param bill 账单
     * @return 返回影响行数
     */
    Integer delByBill(Bill bill)throws Exception;

    /**
     * 修改账单明细
     * @param bill 账单
     * @return 返回影响行数
     */
    Integer updateByBill(Bill bill)throws Exception;

}
