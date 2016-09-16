package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;
import java.util.List;

import java.util.Date;
import java.util.Map;

/**
 * Created by 中希 on 2016/8/2.
 */
public interface SeckillDAO {

    /**
     * 减库存操作
     * @param seckillId
     * @param killTime
     * @return
     */
    int ReduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime) ;

    /**
     * 根据ID查询具体明细
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 批量列出
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);


    /**
     *  使用存储过程
     * @param paraMap
     */
    void killByProcedure(Map<String, Object> paraMap);
}
