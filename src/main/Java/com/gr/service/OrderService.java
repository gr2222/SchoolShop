package com.gr.service;

import com.gr.dto.OrderExecution;
import com.gr.dto.ProductExecution;
import com.gr.entity.Order;
import com.gr.entity.Product;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 20:25
 */
public interface OrderService {
    /**
     * 添加订单
     * @param order 订单信息
     * @return 是否成功
     */
    Boolean addOrder(Order order);

    /**
     * 根据传入的信息查询订单
     * @param order 信息
     * @param pageNum 第几页
     * @param pageSize 每一页的大小
     * @return 订单信息
     */
    OrderExecution getOrderList(Order order, int pageNum, int pageSize);

    /**
     * 设置 uuid和enableStatus
     * @param order 信息
     * @return
     */
    OrderExecution setOrderUuidAndEnableStatus(Order order);
}
