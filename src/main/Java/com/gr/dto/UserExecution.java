package com.gr.dto;

import com.gr.entity.LocalAuth;
import com.gr.entity.PersonInfo;
import com.gr.enums.UserStateEnum;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 14:43
 */
public class UserExecution {
    private int state;
    private String stateInfo;
    private int count;
    private LocalAuth localAuth;
    private PersonInfo personInfo;
    public UserExecution(UserStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }
    public UserExecution(UserStateEnum stateEnum,LocalAuth localAuth){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.localAuth = localAuth;
    }
    public UserExecution(UserStateEnum stateEnum,PersonInfo personInfo){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.personInfo = personInfo;
    }
    public UserExecution(UserStateEnum stateEnum,PersonInfo personInfo,LocalAuth localAuth){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.personInfo = personInfo;
        this.localAuth = localAuth;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public LocalAuth getLocalAuth() {
        return localAuth;
    }

    public void setLocalAuth(LocalAuth localAuth) {
        this.localAuth = localAuth;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }
}
