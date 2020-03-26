package com.gr.entity;

import com.baomidou.mybatisplus.annotations.TableName;

import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-24 20:16
 */
@TableName(value = "tb_order_summary")
public class OrderSummary {
    private Long orderSummaryId;
    private Long shopId;
    private Product product;
    private Long orderNum;
    private Date createTime;

    public Long getOrderSummaryId() {
        return orderSummaryId;
    }

    public void setOrderSummaryId(Long orderSummaryId) {
        this.orderSummaryId = orderSummaryId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"orderSummaryId\":")
                .append(orderSummaryId);
        sb.append(",\"shopId\":")
                .append(shopId);
        sb.append(",\"product\":")
                .append(product);
        sb.append(",\"orderNum\":")
                .append(orderNum);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
