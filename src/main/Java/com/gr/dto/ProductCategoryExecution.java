package com.gr.dto;

import com.gr.entity.ProductCategory;
import com.gr.enums.ProductCategoryEnum;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 17:17
 */
public class ProductCategoryExecution {
    private int state;
    private String stateInfo;
    private List<ProductCategory> list;

    public ProductCategoryExecution() {
    }

    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum) {
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
    }

    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum, List<ProductCategory> list) {
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
        this.list = list;
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

    public List<ProductCategory> getList() {
        return list;
    }

    public void setList(List<ProductCategory> list) {
        this.list = list;
    }
}
