package com.gr.entity;

import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-11 13:14
 */
public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;
    private String advice;
    private Area area;
    private PersonInfo personInfo;
    private ShopCategory shopCategory;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDesc() {
        return shopDesc;
    }

    public void setShopDesc(String shopDesc) {
        this.shopDesc = shopDesc;
    }

    public String getShopAddr() {
        return shopAddr;
    }

    public void setShopAddr(String shopAddr) {
        this.shopAddr = shopAddr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getShopImg() {
        return shopImg;
    }

    public void setShopImg(String shopImg) {
        this.shopImg = shopImg;
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

    public Integer getEnableStatus() {
        return enableStatus;
    }

    public void setEnableStatus(Integer enableStatus) {
        this.enableStatus = enableStatus;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public ShopCategory getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(ShopCategory shopCategory) {
        this.shopCategory = shopCategory;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"shopId\":")
                .append(shopId);
        sb.append(",\"shopName\":\"")
                .append(shopName).append('\"');
        sb.append(",\"shopDesc\":\"")
                .append(shopDesc).append('\"');
        sb.append(",\"shopAddr\":\"")
                .append(shopAddr).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"shopImg\":\"")
                .append(shopImg).append('\"');
        sb.append(",\"priority\":")
                .append(priority);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastEditTime\":\"")
                .append(lastEditTime).append('\"');
        sb.append(",\"enableStatus\":")
                .append(enableStatus);
        sb.append(",\"advice\":\"")
                .append(advice).append('\"');
        sb.append(",\"area\":")
                .append(area);
        sb.append(",\"personInfo\":")
                .append(personInfo);
        sb.append(",\"shopCategory\":")
                .append(shopCategory);
        sb.append('}');
        return sb.toString();
    }
}
