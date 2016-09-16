package org.seckill.enums;

/**
 * Created by 中希 on 2016/8/10.
 */
public enum SeckillState {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀活动已结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"内部错误"),
    WRONG_PARAM(-3,"传参错误");

    private int state ;

    private String stateInfo ;

    SeckillState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillState stateOf(int index){
        for(SeckillState s : values()){
            if(s.getState() == index){
                return s ;
            }
        }
        return  null ;
    }
}
