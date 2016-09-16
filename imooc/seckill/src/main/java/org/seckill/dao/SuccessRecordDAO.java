package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import org.seckill.entity.SuccessRecord;

/**
 * Created by 中希 on 2016/8/2.
 */
public interface SuccessRecordDAO {

    /**
     * 插入购买明细
     * @param seckillId
     * @param phone
     * @return
     */
    int insertRecord(@Param("seckillId") long seckillId, @Param("phone") long phone);

    /**
     * 返回秒杀明细
     * @param seckillId
     * @return
     */
    SuccessRecord queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("phone") long phone);
}
