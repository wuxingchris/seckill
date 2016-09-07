package com.chris.dao;

import com.chris.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

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
    int insertSuccessKilled(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);

    /**
     * 通过ID查询SuccessKilled
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId, @Param("userPhone")long userPhone);
}
