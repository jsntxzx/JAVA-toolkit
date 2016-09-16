package org.seckill.dto;

import org.seckill.entity.SuccessRecord;
import org.seckill.enums.SeckillState;

/**
 * Created by 中希 on 2016/8/9.
 */
public class Excuter {

    /**
     * 秒杀的ID
     */
    private long seckillId ;

    /**
     * 秒杀执行结果的状态
     */
    private int state ;

    /**
     * 秒杀执行结果的描述
     */
    private String stateDescrition ;

    /**
     * 秒杀执行成功时的返回值
     */
    private SuccessRecord successRecord ;

    @Override
    public String toString() {
        return "Excuter{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateDescrition='" + stateDescrition + '\'' +
                ", successRecord=" + successRecord +
                '}';
    }

    /**
     * 成功时的执行结果
     * @param seckillId
     * @param state
     * @param successRecord
     */
    public Excuter(long seckillId, SeckillState state, SuccessRecord successRecord) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateDescrition = state.getStateInfo();
        this.successRecord = successRecord;
    }

    /**
     * 失败时的执行结果
     * @param seckillId
     * @param state
     */
    public Excuter(long seckillId, SeckillState state) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateDescrition = state.getStateInfo();
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateDescrition() {
        return stateDescrition;
    }

    public void setStateDescrition(String stateDescrition) {
        this.stateDescrition = stateDescrition;
    }

    public SuccessRecord getSuccessRecord() {
        return successRecord;
    }

    public void setSuccessRecord(SuccessRecord successRecord) {
        this.successRecord = successRecord;
    }
}
