package org.seckill.dto;

import java.util.Date;

/**
 * Created by 中希 on 2016/8/9.
 */
public class Exposer {

    /**
     * 是否开启秒杀
     */
    private boolean flag;


    /**
     * 秒杀活动的URL(使用MD5进行加密)
     */
    private String md5 ;


    /**
     * 秒杀活动的Id
     */
    private long seckillId ;

    /**
     * 秒杀开始时间(都是以毫秒进行计时)
     */
    private long startTime ;

    /**
     * 秒杀结束时间
     */
    private long endTime ;

    /**
     * 当前的系统时间
     */
    private long sysTime ;


    public Exposer(boolean flag, String md5, long seckillId) {
        this.flag = flag;
        this.md5 = md5;
        this.seckillId = seckillId;
    }


    public Exposer(boolean flag, long startTime, long endTime, long sysTime) {
        this.flag = flag;
        this.startTime = startTime;
        this.endTime = endTime;
        this.sysTime = sysTime;
    }

    public Exposer(boolean flag, long seckillId) {
        this.flag = flag;
        this.seckillId = seckillId;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getSysTime() {
        return sysTime;
    }

    public void setSysTime(long sysTime) {
        this.sysTime = sysTime;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "flag=" + flag +
                ", md5='" + md5 + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sysTime=" + sysTime +
                '}';
    }
}


