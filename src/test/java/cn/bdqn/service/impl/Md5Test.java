package cn.bdqn.service.impl;

import cn.bdqn.util.md5.MyMd5;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
/**
 * Created by hasee on 2017/8/24.
 */
public class Md5Test {
    Logger logger=Logger.getLogger(Md5Test.class);
    @Test
    public void tomd5(){
        String md5= MyMd5.toMd5String("ceshi");
        logger.debug(md5.length());
        logger.debug(md5);
    }
}
