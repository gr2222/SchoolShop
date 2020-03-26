package com.gr.enums;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 14:44
 */
public enum UserStateEnum {
    SUCCESS(1, "成功"), PASS(2, "通过"), INNER_ERROP(-1000, "内部错误"),NOT_USER(-2,"信息为空");
    private int state;
    private String stateInfo;

    private UserStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static UserStateEnum getUserState(int state) {
        for (UserStateEnum stateEnum : values()) {
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
