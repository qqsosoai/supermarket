package cn.bdqn.service.impl;

import cn.bdqn.bean.Bill;
import cn.bdqn.dao.BillMapper;
import cn.bdqn.service.BillService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by hasee on 2017/8/10.
 * 账单service层
 */
public class BillServiceImpl implements BillService {
    @Resource(name = "billDao")
    private BillMapper dao;
    public boolean addService(Bill bill)throws Exception {
        boolean flag=false;
        Integer count = dao.addByBill(bill);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public boolean updateService(Bill bill) throws Exception{
        boolean flag=false;
        Integer count = dao.updateByBill(bill);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public boolean delService(Bill bill) throws Exception{
        boolean flag=false;
        Integer count=dao.delByBill(bill);
        if (count>0){
            flag=true;
        }
        return flag;
    }

    public List<Bill> findByLimit(String name, Integer proId, Integer isPay, Integer pageIndex, Integer pageSize)
            throws Exception {
        if (pageIndex!=null){
            pageIndex=(pageIndex-1)*pageSize;
        }
        return dao.findByLimitBillName(name,proId,isPay,pageIndex,pageSize);
    }

    public Integer findBySqlCount(String name,Integer proId,Integer isPay)throws Exception {
        return dao.findBySqlCount(name,proId,isPay);
    }

    public Bill findById(Integer id)throws Exception {
        return dao.findById(id);
    }

}
