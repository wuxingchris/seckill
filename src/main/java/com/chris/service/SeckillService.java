package com.chris.service;

import com.chris.entity.Seckill;
import com.chris.exception.RepeatKillException;
import com.chris.exception.SeckillCloseException;
import com.chris.exception.SeckillException;
import com.chris.vo.Exposer;
import com.chris.vo.SeckillExecution;

import java.util.List;

/**
 * Created by wuxing on 2016/9/7.
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getSeckillById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址
     * 否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException;

}
