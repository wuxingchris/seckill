package com.chris.constant;

/**
 * Created by wuxing on 2016/9/7.
 */
public enum SeckillStatus {
    SUCCESS(1, "秒杀成功"), END(0, "秒杀结束"), REPEAT_KILL(-1, "重复秒杀"), INNER_ERROR(-2, "系统异常"), DATA_REWRITE(-3, "数据篡改");


    private int status;
    private String statusInfo;

    SeckillStatus(int status, String statusInfo) {
        this.status = status;
        this.statusInfo = statusInfo;
    }

    public static SeckillStatus statusOf(int index) {
        for (SeckillStatus status : values()) {
            if (status.getStatus() == index) {
                return status;
            }
        }
        return null;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusInfo() {
        return statusInfo;
    }
}
