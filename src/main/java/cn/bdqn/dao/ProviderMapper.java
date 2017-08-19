package cn.bdqn.dao;

import cn.bdqn.bean.Provider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by hasee on 2017/8/9.
 * 供应商的接口
 */
public interface ProviderMapper {
    /**
     * 根据供应商名称分页查询
     * @param proName 供应商名称(模糊查询)
     * @param pageIndex 当前页码
     * @param pageSize 页大小
     * @return 供应商集合
     */
    List<Provider> findByLimitProName(@Param("proName") String proName,
                                         @Param("pageIndex") Integer pageIndex,
                                         @Param("pageSize") Integer pageSize)throws Exception;

    /**
     * 根据条件查询数据库总记录数
     * @param proName 供应商名称(模糊查询)
     * @return 数据库总记录数
     */
    Integer findBySqlCount(@Param("proName") String proName)throws Exception;

    /**
     * 根据ID查询供应商
     * @param id 供应商ID
     * @return 返回供应商详情
     */
    Provider findById(Integer id)throws Exception;

    /**
     * 查询所有供应商ID与名称给账单页面使用
     * @return 供应商集合
     * @throws Exception
     */
    List<Provider> findByProviderToBill()throws Exception;

    /**
     * 新增供应商
     * @param provider 供应商
     * @return 返回影响行数
     */
    Integer addProvider(Provider provider)throws Exception;

    /**
     * 修改供应商详情
     * @param provider 供应商
     * @return 返回影响行数
     */
    Integer updateProvider(Provider provider)throws Exception;

    /**
     * 删除供应商
     * @param provider 供应商
     * @return 返回影响行数
     */
    Integer delProvider(Provider provider)throws Exception;

}
