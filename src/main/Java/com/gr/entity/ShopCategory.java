package com.gr.entity;

import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-11 12:56
 */
public class ShopCategory {
    private Long shopCategoryId;
    private String shopCategoryName;
    private String shopCateGoryDesc;
    private String shopCateGoryImg;
    private Integer priority;
    private ShopCategory parent;
    private Date createTime;
    private Date lastEditTime;

    public Long getShopCategoryId() {
        return shopCategoryId;
    }

    public void setShopCategoryId(Long shopCategoryId) {
        this.shopCategoryId = shopCategoryId;
    }

    public String getShopCategoryName() {
        return shopCategoryName;
    }

    public void setShopCategoryName(String shopCategoryName) {
        this.shopCategoryName = shopCategoryName;
    }

    public String getShopCateGoryDesc() {
        return shopCateGoryDesc;
    }

    public void setShopCateGoryDesc(String shopCateGoryDesc) {
        this.shopCateGoryDesc = shopCateGoryDesc;
    }

    public String getShopCateGoryImg() {
        return shopCateGoryImg;
    }

    public void setShopCateGoryImg(String shopCateGoryImg) {
        this.shopCateGoryImg = shopCateGoryImg;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public ShopCategory getParent() {
        return parent;
    }

    public void setParent(ShopCategory parent) {
        this.parent = parent;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"shopCategoryId\":")
                .append(shopCategoryId);
        sb.append(",\"shopCategoryName\":\"")
                .append(shopCategoryName).append('\"');
        sb.append(",\"shopCateGoryDesc\":\"")
                .append(shopCateGoryDesc).append('\"');
        sb.append(",\"shopCateGoryImg\":\"")
                .append(shopCateGoryImg).append('\"');
        sb.append(",\"priority\":")
                .append(priority);
        sb.append(",\"parent\":")
                .append(parent);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastEditTime\":\"")
                .append(lastEditTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
