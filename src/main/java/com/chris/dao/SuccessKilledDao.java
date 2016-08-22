package com.chris.dao;

import com.chris.entity.SuccessKilled;

/**
 * Created by wuxing on 2016/8/20.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细, 可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(long seckillId, long userPhone);

    /**
     * 通过ID查询SuccessKilled
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);
}
