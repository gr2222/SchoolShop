package com.gr.dto;

import com.gr.entity.OrderSummary;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-25 12:50
 */
// {'categ': ['男', '女'],
// 'data': [[2, 136, 38, 4, 1, 0], [3, 75, 25, 5, 1, 0]],
// 'name': ['青少年', '青年人', '中年人', '老年前期', '老年人', '长寿老人']}


public class OrderSummaryExecution {
    private String productName;
    private Long yeterdayOrderNum;
    private Long terdayOrderNum;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getYeterdayOrderNum() {
        return yeterdayOrderNum;
    }

    public void setYeterdayOrderNum(Long yeterdayOrderNum) {
        this.yeterdayOrderNum = yeterdayOrderNum;
    }

    public Long getTerdayOrderNum() {
        return terdayOrderNum;
    }

    public void setTerdayOrderNum(Long terdayOrderNum) {
        this.terdayOrderNum = terdayOrderNum;
    }
}
