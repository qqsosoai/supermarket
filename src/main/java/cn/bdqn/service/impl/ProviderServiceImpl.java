package cn.bdqn.service.impl;

import cn.bdqn.bean.Provider;
import cn.bdqn.dao.ProviderMapper;
import cn.bdqn.service.ProviderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hasee on 2017/8/10.
 * 供应商service层
 */
public class ProviderServiceImpl implements ProviderService {
    @Resource(name = "providerDao")
    private ProviderMapper dao;
    public boolean addService(Provider provider) throws Exception {
        boolean flag=false;
        Integer count = dao.addProvider(provider);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public boolean updateService(Provider provider) throws Exception {
        boolean flag=false;
        Integer count = dao.updateProvider(provider);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public boolean delService(Provider provider) throws Exception {
        boolean flag=false;
        Integer count = dao.delProvider(provider);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public List<Provider> findByNameLimit(String proName, Integer pageIndex, Integer pageSize) throws Exception {
        if (pageIndex!=null && pageSize!=null){
            pageIndex=(pageIndex-1)*pageSize;
        }
        return dao.findByLimitProName(proName,pageIndex,pageSize);
    }

    public Integer findByNameSqlCount(String proName) throws Exception {
        return dao.findBySqlCount(proName);
    }

    public List<Provider> findByProviderToBill() throws Exception {
        return dao.findByProviderToBill();
    }

    public Provider findById(Integer id) throws Exception {
        return dao.findById(id);
    }

}
