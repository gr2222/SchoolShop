package com.gr.enums;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 17:19
 */
public enum ProductCategoryEnum {
    SUCCESS(1,"成功"),INNER_ERROR(-1000,"操作失败"),EMPTY_LIST(-1001,"添加数少于1");
    private int state;
    private String stateInfo;

    ProductCategoryEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static ProductCategoryEnum getShopState(int state) {
        for (ProductCategoryEnum productCategoryEnum : values()) {
            if (productCategoryEnum.getState() == state) {
                return productCategoryEnum;
            }
        }
        return null;
    }
    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
