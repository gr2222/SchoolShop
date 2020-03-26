package com.gr.entity;

import java.util.Date;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-11 10:31
 */
public class Area {

    private Integer areaId;

    private String areaName;

    private Integer priority;

    private Date createTime;

    private Date lastEditTime;

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"areaId\":")
                .append(areaId);
        sb.append(",\"areaName\":\"")
                .append(areaName).append('\"');
        sb.append(",\"priority\":")
                .append(priority);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"lastEditTime\":\"")
                .append(lastEditTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
