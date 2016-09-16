package org.seckill.service;

import org.seckill.dto.Excuter;
import org.seckill.dto.Exposer;
import org.seckill.entity.Seckill;
import org.seckill.exception.ClosedKillException;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;

import java.util.List;

/**
 * Created by 中希 on 2016/8/9.
 */
public interface SeckillService {

    /**
     *
     * 返回秒杀活动列表
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 根据id获取Seckill对象
     * @param SeckillId
     * @return
     */
    Seckill getbyId(long SeckillId);


    /**
     * 根据秒杀业务的时间判断当前的返回值
     * 如果秒杀开启 返回的是秒杀的URL
     * 如果未开启 返回的是当前的系统时间和秒杀开始的时间
     * @param SeckillId
     * @return
     */
     Exposer seckillExpose(long SeckillId);


    /**
     * 执行秒杀 并返回秒杀的结果
     * @param SeckillId
     * @param phone
     * @param md5
     */
    Excuter seckillExcute(long SeckillId, long phone, String md5)
            throws SeckillException, RepeatKillException, ClosedKillException;

    Excuter seckillExcuteProcedure(long SeckillId, long phone, String md5)
            throws SeckillException, RepeatKillException, ClosedKillException;
}
