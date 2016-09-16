package org.seckill.entity;

import java.util.Date;

/**
 * Created by 中希 on 2016/8/2.
 */
public class SuccessRecord {
    private long seckillId ;

    private long phone ;

    private Date time ;

    private short state ;

    private Seckill seckill ;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    @Override
    public String toString() {
        return "SuccessRecord{" +
                "seckillId=" + seckillId +
                ", phone=" + phone +
                ", time=" + time +
                ", state=" + state +
                ", seckill=" + seckill +
                '}';
    }
}
