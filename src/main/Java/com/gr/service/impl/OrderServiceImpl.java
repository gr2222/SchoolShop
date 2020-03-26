package com.gr.service.impl;

import com.gr.dao.OrderDao;
import com.gr.dto.OrderExecution;
import com.gr.entity.Order;
import com.gr.enums.OrderSateEnum;
import com.gr.service.OrderService;
import com.gr.util.PageMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 20:26
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public Boolean addOrder(Order order) {
        int i = orderDao.insertOrder(order);
        if (i == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public OrderExecution getOrderList(Order order, int pageNum, int pageSize) {
        int rowIndex = PageMath.pageNumtoRowIndex(pageNum, pageSize);
        List<Order> orders = orderDao.queryOrder(order, rowIndex, pageSize);
        int i = orderDao.queryOrderCount(order);
        OrderExecution orderExecution = null;
        if (orders.size() == 0) {
            orderExecution = new OrderExecution(OrderSateEnum.DATA_NULL);
            orderExecution.setCount(i);
        }
        if (orders.size() < 0) {
            orderExecution = new OrderExecution(OrderSateEnum.INNER_ERR);
        }
        if (orders.size() > 0) {
            orderExecution = new OrderExecution(OrderSateEnum.SUCCESS, orders);
            orderExecution.setCount(i);
        }
        return orderExecution;
    }

    @Override
    public OrderExecution setOrderUuidAndEnableStatus(Order order) {
        int i = orderDao.updateOrder(order);
        OrderExecution orderExecution = null;
        if (i > 0) {
            orderExecution = new OrderExecution(OrderSateEnum.SUCCESS);
        } else {
            orderExecution = new OrderExecution(OrderSateEnum.INNER_ERR);
        }

        return orderExecution;
    }
}
