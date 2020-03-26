package com.gr.enums;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 20:58
 */
public enum ShopSateEnum {
    REVIEW(0, "审核中"), ILLEGAL(-1, "非法店铺"), SUCCESS(1, "成功"),
    PASS(2, "通过认真"), INNER_ERROP(-1000, "内部错误"),NOT_SHOP(-2,"shop信息为空");
    private int state;
    private String stateInfo;

    private ShopSateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static ShopSateEnum getShopState(int state) {
        for (ShopSateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
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
