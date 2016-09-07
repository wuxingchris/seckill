package com.chris.exception;

/**
 * 秒杀关闭异常
 * Created by wuxing on 2016/9/7.
 */
public class SeckillCloseException extends SeckillException{
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
