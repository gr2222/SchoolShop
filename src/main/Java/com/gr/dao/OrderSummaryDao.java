package com.gr.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gr.entity.OrderSummary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-24 20:19
 */
public interface OrderSummaryDao extends BaseMapper<OrderSummary> {
    /**
     * 插入订单汇总信息
     *
     * @param orderSummary 订单汇总信息
     * @return 影响的行数
     */
    int insertOrderSummary(OrderSummary orderSummary);

    /**
     * 根据商铺id 查询销量
     *
     * @param shopId 商铺id
     * @param date   时间
     * @return
     */
    List<OrderSummary> selectOrderSummary(@Param("shopId") Long shopId, @Param("date") Date date);

}
