package com.chris.entity;

import java.util.Date;

/**
 * 成功秒杀明细类
 * Created by wuxing on 2016/8/20.
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private int status;
    private Date createTime;
    private Seckill seckill;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }
}
