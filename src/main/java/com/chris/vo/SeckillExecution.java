package com.chris.vo;

import com.chris.constant.SeckillStatus;
import com.chris.entity.SuccessKilled;

/**
 * 秒杀执行结果vo
 * Created by wuxing on 2016/9/7.
 */
public class SeckillExecution {

    private long seckillId;
    private int status;
    private String statusInfo;
    private SuccessKilled successKilled;

    public SeckillExecution(long seckillId, SeckillStatus seckillStatus, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.status = seckillStatus.getStatus();
        this.statusInfo = seckillStatus.getStatusInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(long seckillId, SeckillStatus seckillStatus) {
        this.seckillId = seckillId;
        this.status = seckillStatus.getStatus();
        this.statusInfo = seckillStatus.getStatusInfo();
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

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", status=" + status +
                ", statusInfo='" + statusInfo + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}
