package cn.bdqn.service.impl;

import cn.bdqn.bean.Bill;
import cn.bdqn.bean.Provider;
import cn.bdqn.service.BillService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hasee on 2017/8/10.
 */
public class BillServiceImplTest {
    private ApplicationContext ac;
    private BillService service;
    private Logger log= Logger.getLogger(BillServiceImplTest.class);
    @Before
    public void setUp() throws Exception {
        ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        service= (BillService) ac.getBean("billService");
    }

    @Test
    public void addService() throws Exception {
        Bill bill=new Bill(new Provider(4),"009","加多宝凉茶",null,"箱",new BigDecimal(10),new BigDecimal(2000), 0, 2);
        boolean flag = service.addService(bill);
        assertTrue(flag);
    }

    @Test
    public void updateService() throws Exception {
        Bill bill=new Bill(new Provider(1), "伊利牛奶牛牛", null, "卡车",new BigDecimal(3),new BigDecimal(200),1,2,1);
        boolean flag = service.updateService(bill);
        assertTrue(flag);
    }

    @Test
    public void delService() throws Exception {
        Bill bill=new Bill();
        bill.setId(10);
        bill.setProductName("蒙牛");
        boolean flag = service.delService(bill);
        assertTrue(flag);
    }

    @Test
    public void findByLimit() throws Exception {
        List<Bill> bills = service.findByLimit(null, null, 1, 1, 3);
        assertEquals(3,bills.size());
    }

    @Test
    public void findBySqlCount() throws Exception {
        Integer count = service.findBySqlCount(null, 1, 1);
        assertEquals(2,count.intValue());
    }

    @Test
    public void findById() throws Exception {
        Bill bill = service.findById(2);
        log.debug(bill.getProId());
        log.debug(bill);
    }

}