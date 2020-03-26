package com.gr.entity;

import java.util.Date;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-11 13:46
 */
public class Product {
    private Long productId;
    private String productName;
    private String productDesc;
    /**
     * 简略图
     */
    private String imgAddr;
    private String normalPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private Integer integral;
    /**
     * -1不可用，0下架 1在前端页面展示
     */
    private Integer enableStatus;

    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Shop shop;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public String getImgAddr() {
        return imgAddr;
    }

    public void setImgAddr(String imgAddr) {
        this.imgAddr = imgAddr;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public Integer getIntegral() {
        return integral;
    }

    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public List<ProductImg> getProductImgList() {
        return productImgList;
    }

    public void setProductImgList(List<ProductImg> productImgList) {
        this.productImgList = productImgList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"productId\":")
                .append(productId);
        sb.append(",\"productName\":\"")
                .append(productName).append('\"');
        sb.append(",\"productDesc\":\"")
                .append(productDesc).append('\"');
        sb.append(",\"imgAddr\":\"")
                .append(imgAddr).append('\"');
        sb.append(",\"normalPrice\":\"")
                .append(normalPrice).append('\"');
        sb.append(",\"promotionPrice\":\"")
                .append(promotionPrice).append('\"');
        sb.append(",\"priority\":")
                .append(priority);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastEditTime\":\"")
                .append(lastEditTime).append('\"');
        sb.append(",\"integral\":")
                .append(integral);
        sb.append(",\"enableStatus\":")
                .append(enableStatus);
        sb.append(",\"productImgList\":")
                .append(productImgList);
        sb.append(",\"productCategory\":")
                .append(productCategory);
        sb.append(",\"shop\":")
                .append(shop);
        sb.append('}');
        return sb.toString();
    }
}
