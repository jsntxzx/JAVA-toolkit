package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by 中希 on 2016/8/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDAOTest {
    private long id = 2003 ;
    @Autowired
    private RedisDAO redisDAO ;

    @Autowired
    private SeckillDAO seckillDAO ;

    @Test
    public void testSeckill() throws Exception {
        Seckill seckill = redisDAO.getSeckill(id) ;
        if(seckill == null){
            seckill = seckillDAO.queryById(id) ;
            if(seckill != null){
                String result = redisDAO.setSeckill(seckill);
                System.out.println(result);
                seckill = redisDAO.getSeckill(id) ;
                System.out.println(seckill);
            }
        }
    }

}