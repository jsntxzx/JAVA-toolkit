package org.seckill.exception;

/**
 * Created by 中希 on 2016/8/9.
 */
public class RepeatKillException extends SeckillException{

    public RepeatKillException(String message){
        super(message);
    }

    public RepeatKillException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
