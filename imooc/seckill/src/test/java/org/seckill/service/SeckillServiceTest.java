package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Excuter;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.exception.ClosedKillException;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by 中希 on 2016/8/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> l = seckillService.getSeckillList();
        logger.info("seckill list is {}", l);
    }

    @Test
    public void getbyId() throws Exception {
        long id = 2002;
        Seckill s = seckillService.getbyId(id);
        logger.info("seckill object is{}", s);
    }

    @Test
    public void seckillLogicTest() throws Exception {
        long id = 2003;
        Exposer e = seckillService.seckillExpose(id);
        if (e.isFlag()) {
            logger.info("exposer is {}", e);
            long phone = 17095335596L;
            String md5 = e.getMd5();
            try {
                Excuter e1 = seckillService.seckillExcute(id, phone, md5);
                logger.info("excuter is {}", e1);
            } catch (RepeatKillException er) {
                logger.error(er.getMessage(), er);
            } catch (ClosedKillException er) {
                logger.error(er.getMessage(), er);
            } catch (SeckillException er) {
                logger.error(er.getMessage(), er);
            }
        } else {
            logger.warn("excuter is {}", e);
        }


    }

    @Test
    public void seckillProcedureTest() throws Exception {
        long id = 2003;
        long phone = 1709535586L ;
        Exposer exposer = seckillService.seckillExpose(id);
        if(exposer.isFlag()){
            String md5 = exposer.getMd5() ;
            Excuter excuter = seckillService.seckillExcuteProcedure(id, phone, md5);
            logger.info(excuter.getStateDescrition());
        }


    }


}