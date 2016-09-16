package org.seckill.service.impl;


import org.apache.commons.collections.MapUtils;
import org.seckill.dao.RedisDAO;
import org.seckill.dao.SeckillDAO;
import org.seckill.dao.SuccessRecordDAO;
import org.seckill.dto.Excuter;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessRecord;
import org.seckill.enums.SeckillState;
import org.seckill.exception.ClosedKillException;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

/**
 * Created by 中希 on 2016/8/9.
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    /**
     * 加盐MD5
     */
    private final String salt = "terytdasdasdadasd";
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDAO seckillDAO;

    @Autowired
    private SuccessRecordDAO successRecordDAO;

    @Autowired
    private RedisDAO redisDAO;

    /**
     * 生成MD5的函数
     *
     * @param seckillId
     * @return
     */
    private String generateMD5(long seckillId) {
        String base = seckillId + "||" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    /**
     * 返回秒杀活动列表
     *
     * @return
     */
    public List<Seckill> getSeckillList() {
        return seckillDAO.queryAll(0, 4);
    }

    /**
     * 根据id获取Seckill对象
     *
     * @param SeckillId
     * @return
     */
    public Seckill getbyId(long SeckillId) {
        return seckillDAO.queryById(SeckillId);
    }

    /**
     * 根据秒杀业务的时间判断当前的返回值
     * 如果秒杀开启 返回的是秒杀的URL
     * 如果未开启 返回的是当前的系统时间和秒杀开始的时间
     *
     * @param SeckillId
     * @return
     */
    public Exposer seckillExpose(long SeckillId) {
        Exposer ret = null;
        Seckill seckill = redisDAO.getSeckill(SeckillId);
        if (seckill == null) {
            seckill = seckillDAO.queryById(SeckillId);
            if (seckill == null) {
                return new Exposer(false, SeckillId);
            } else {
                redisDAO.setSeckill(seckill);
            }
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date now = new Date();
        if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
            ret = new Exposer(false, startTime.getTime(), endTime.getTime(), now.getTime());
        }
        String md5 = generateMD5(SeckillId);
        ret = new Exposer(true, md5, SeckillId);
        return ret;
    }

    /**
     * 执行秒杀 并返回秒杀的结果
     *
     * @param SeckillId
     * @param phone
     * @param md5
     */
    @Transactional
    /*
    *  说明 1 所有声明式事务执行时间最好尽可能地短 ， 不要穿插RPC和HTTP的请求
    *      2  不是所有执行SQL语句的地方都要用事务 ，只有一条修改操作或者只有只读操作不需要事务(行级锁)
    * */
    public Excuter seckillExcute(long SeckillId, long phone, String md5) throws SeckillException, RepeatKillException, ClosedKillException {
        if (md5 == null || !md5.equals(generateMD5(SeckillId))) {
            throw new SeckillException("seckill params error");
        }
        Date now = new Date();
        try {
            int insert_count = successRecordDAO.insertRecord(SeckillId, phone);
            if (insert_count <= 0) {
                throw new RepeatKillException("seckill is repeated");
            } else {
                int update_count = seckillDAO.ReduceNumber(SeckillId, now);
                if (update_count <= 0) {
                    throw new ClosedKillException("seckill is closed");
                } else {
                    SuccessRecord successRecord = successRecordDAO.queryByIdWithSeckill(SeckillId, phone);
                    return new Excuter(SeckillId, SeckillState.SUCCESS, successRecord);
                }
            }
        } catch (ClosedKillException e1) {
            throw e1;
        } catch (RepeatKillException e2) {
            throw e2;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill error occors");
        }

    }

    public Excuter seckillExcuteProcedure(long SeckillId, long phone, String md5)
            throws SeckillException, RepeatKillException, ClosedKillException {
        if (md5 == null || !md5.equals(generateMD5(SeckillId))) {
            throw new SeckillException("seckill params error");
        }
        Date now = new Date();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("seckillId", SeckillId);
        map.put("phone", phone);
        map.put("killTime", now);
        map.put("result", null);
        try {
            seckillDAO.killByProcedure(map);
            int result = MapUtils.getInteger(map, "result", -2);
            if (result == 1) {
                SuccessRecord successRecord = successRecordDAO.queryByIdWithSeckill(SeckillId, phone);
                return new Excuter(SeckillId, SeckillState.SUCCESS, successRecord);
            } else {
                return new Excuter(SeckillId, SeckillState.stateOf(result));
            }
        } catch (Exception e) {
            return new Excuter(SeckillId, SeckillState.INNER_ERROR);
        }

    }
}
