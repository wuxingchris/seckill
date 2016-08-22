package com.chris.dao;

import com.chris.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wuxing on 2016/8/20.
 */
public interface SeckillDao {
    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);


    /**
     * 通过ID查询秒杀商品
     *
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);


    /**
     * 查询秒杀商品列表
     *
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
