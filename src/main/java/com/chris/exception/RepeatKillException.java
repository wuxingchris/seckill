package com.chris.exception;

/**
 * 重复秒杀异常
 * Created by wuxing on 2016/9/7.
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
