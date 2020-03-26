package com.gr.dao;

import com.gr.entity.Order;
import com.gr.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 19:58
 */
public interface OrderDao {
    /**
     * 添加订单
     *
     * @param order 订单信息
     * @return 返回影响行数
     */
    int insertOrder(Order order);

    /**
     * 根据部分信息查询订单
     *
     * @param order    信息
     * @param rowIndex 几行
     * @param pageSize 每一页的大小
     * @return 返回查询的订单
     */
    List<Order> queryOrder(@Param("order") Order order, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

    /**
     * 配合queryShops方法使用 得到总数
     * @param order
     * @return
     */
    int queryOrderCount(@Param("order") Order order);

    /**
     * 更新 订单
     * @param order 要修改的信息
     * @return
     */
    int updateOrder(Order order);
}

