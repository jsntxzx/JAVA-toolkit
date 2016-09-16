package org.seckill.dao;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by 中希 on 2016/8/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDAOTest {

    @Resource
    private SeckillDAO seckillDao ;

    @Test
    public void reduceNumber() throws Exception {
        Date d = new Date();
        int updateCount = seckillDao.ReduceNumber(2000L, d);
        System.out.println("update count is " + updateCount);
    }

    @Test
    public void queryById() throws Exception {
        long id = 2000 ;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        List<Seckill> seckillList = seckillDao.queryAll(0,100);
        for (Seckill s : seckillList)
        {
            System.out.println(s);
        }
    }

}