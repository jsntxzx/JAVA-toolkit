package org.seckill.exception;

/**
 * Created by 中希 on 2016/8/9.
 */
public class ClosedKillException extends SeckillException {
    public ClosedKillException(String message){
        super(message);
    }

    public ClosedKillException(String message, Throwable cause){
        super(message, cause);
    }
}
