package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessRecord;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by 中希 on 2016/8/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessRecordDAOTest {
    @Resource
    private SuccessRecordDAO successRecordDAO ;

    @Test
    public void insertRecord() throws Exception {
        long id = 2003L;
        long phone = 17095335586L;
        int successCount = successRecordDAO.insertRecord(id, phone);
        System.out.println("success insert count is " + successCount);
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long id = 2003L;
        long phone = 17095335586L;
        SuccessRecord  record = successRecordDAO.queryByIdWithSeckill(id, phone);
        Seckill s = record.getSeckill() ;
        System.out.println(record);
        System.out.println(s);
    }

}