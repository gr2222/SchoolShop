package com.gr.enums;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:39
 */
public enum OrderSateEnum {
    SUCCESS(1,"成功"),INNER_ERR(-1000,"内部错误"),
    DATA_ERROR(-1001,"数据错误"),DATA_NULL(0,"数据为空")
    ;
    private int state;
    private String stateInfo;

    OrderSateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static OrderSateEnum getProductState(int state) {
        for (OrderSateEnum productSateEnum : values()) {
            if (productSateEnum.getState() == state) {
                return productSateEnum;
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
