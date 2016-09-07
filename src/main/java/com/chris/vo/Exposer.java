package com.chris.vo;

/**
 * 暴露秒杀地址vo
 * Created by wuxing on 2016/9/7.
 */
public class Exposer {

    private boolean exposed;    //是否开启
    private String md5;
    private long seckillId; //ID
    private long now;   //服务器当前时间
    private long start; //开启时间
    private long end;   //结束时间

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, long now, long end, long start) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.end = end;
        this.now = now;
        this.start = start;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "end=" + end +
                ", exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                '}';
    }
}
