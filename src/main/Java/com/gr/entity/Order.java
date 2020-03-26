package com.gr.entity;

import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 19:40
 */
public class Order {
    private Long orderId;
    private Shop shop;
    private Product product;
    private PersonInfo buyer;
    /**
     * 订单状态1已经购买但是还没有取货 2已经取货。
     */
    private Integer enableStatus;
    private String normalPrice;
    private String promotionPrice;
    private Integer integral;
    private Date createTime;
    private Date lastEditTime;
    private Long sellerId;
    private Long orderuuid;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public PersonInfo getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonInfo buyer) {
        this.buyer = buyer;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(String normalPrice) {
        this.normalPrice = normalPrice;
    }

    public String getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(String promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastEditTime() {
        return lastEditTime;
    }

    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getOrderuuid() {
        return orderuuid;
    }

    public void setOrderuuid(Long orderuuid) {
        this.orderuuid = orderuuid;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"orderId\":")
                .append(orderId);
        sb.append(",\"shop\":")
                .append(shop);
        sb.append(",\"product\":")
                .append(product);
        sb.append(",\"buyer\":")
                .append(buyer);
        sb.append(",\"enableStatus\":")
                .append(enableStatus);
        sb.append(",\"normalPrice\":\"")
                .append(normalPrice).append('\"');
        sb.append(",\"promotionPrice\":\"")
                .append(promotionPrice).append('\"');
        sb.append(",\"integral\":")
                .append(integral);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastEditTime\":\"")
                .append(lastEditTime).append('\"');
        sb.append(",\"sellerId\":")
                .append(sellerId);
        sb.append(",\"orderuuid\":")
                .append(orderuuid);
        sb.append('}');
        return sb.toString();
    }
}
