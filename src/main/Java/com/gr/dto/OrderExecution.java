package com.gr.dto;

import com.gr.entity.Order;
import com.gr.entity.Product;
import com.gr.enums.OrderSateEnum;
import com.gr.enums.ProductSateEnum;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:37
 */
public class OrderExecution {
    private int state;
    private String stateInfo;
    private int count;
    private Order order;
    private List<Order> list;

    public OrderExecution() {
    }

    public OrderExecution(OrderSateEnum orderSateEnum) {
        this.state = orderSateEnum.getState();
        this.stateInfo = orderSateEnum.getStateInfo();
    }

    public OrderExecution(OrderSateEnum orderSateEnum, Order order) {
        this.state = orderSateEnum.getState();
        this.stateInfo = orderSateEnum.getStateInfo();
        this.order = order;
    }

    public OrderExecution(OrderSateEnum orderSateEnum, List<Order> orders) {
        this.state = orderSateEnum.getState();
        this.stateInfo = orderSateEnum.getStateInfo();
        this.list = orders;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<Order> getList() {
        return list;
    }

    public void setList(List<Order> list) {
        this.list = list;
    }
}
