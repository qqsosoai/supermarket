package cn.bdqn.service.impl;

import cn.bdqn.bean.Provider;
import cn.bdqn.service.ProviderService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by hasee on 2017/8/10.
 */
public class ProviderServiceImpleTest {
    private ApplicationContext ac;
    private ProviderService service;
    private Logger log=Logger.getLogger(ProviderServiceImpleTest.class);
    @Before
    public void setUp() throws Exception {
        ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        service= (ProviderService) ac.getBean("providerService");
    }

    @Test
    public void addService() throws Exception {
        Provider provider=new Provider("006", "加百利", "吸血鬼", "加百利", "大人", "1336698146", null, 3);
        boolean flag = service.addService(provider);
        assertTrue(flag);
    }

    @Test
    public void updateService() throws Exception {
        Provider provider=new Provider(5, "修改多宝丽", "多宝丽日化有限公司", "李先生", "13333333333", "修改", null, 3);
        boolean flag = service.updateService(provider);
        assertTrue(flag);
    }

    @Test
    public void delService() throws Exception {
        Provider provider=new Provider();
        provider.setId(5);
        boolean flag = service.delService(provider);
        log.debug(flag);
        assertTrue(flag);
    }

    @Test
    public void findByNameLimit() throws Exception {
        List<Provider> list = service.findByNameLimit("伊", 1, 3);
        assertEquals(1,list.size());
    }

    @Test
    public void findByNameSqlCount() throws Exception {
        Integer count = service.findByNameSqlCount("伊");
        assertEquals(1,count.intValue());
    }

    @Test
    public void findById() throws Exception {
        Provider provider = service.findById(1);
        log.debug(provider);
    }

}