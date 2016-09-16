package org.seckill.dao;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.seckill.entity.Seckill;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by 中希 on 2016/8/22.
 */
public class RedisDAO {

    private final JedisPool jedisPool;

    private RuntimeSchema<Seckill> schema = RuntimeSchema.createFrom(Seckill.class);

    public RedisDAO(String ip, Integer port) {
        jedisPool = new JedisPool(ip, port);
    }

    public Seckill getSeckill(long seckillId) {
        Jedis jedis = jedisPool.getResource();
        try {
            String key = "seckill:" + seckillId;
            byte[] bytes = jedis.get(key.getBytes());
            if (bytes != null) {
                Seckill seckill = schema.newMessage();
                ProtostuffIOUtil.mergeFrom(bytes, seckill, schema);
                return seckill;
            }
        } finally {
            jedis.close();
        }
        return null;
    }

    public String setSeckill(Seckill seckill) {
        Jedis jedis = jedisPool.getResource();
        try {
            String key = "seckill:" + seckill.getSeckillId();
            byte[] bytes = ProtostuffIOUtil.toByteArray(seckill, schema,
                    LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
            int timeout = 60 * 60;
            String result = jedis.setex(key.getBytes(), timeout, bytes);
            return result;
        } finally {
            jedis.close();
            return null;
        }
    }


}
