package com.gr.service;

import com.gr.dto.ImageHolder;
import com.gr.dto.UserExecution;
import com.gr.entity.LocalAuth;
import com.gr.entity.PersonInfo;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 14:42
 */
public interface UserService {
    String HEADIMAGEURL="/item/head/";
    /**
     * 创建用户
     * @param localAuth 用户
     * @param personInfo 用户信息
     * @param imageHolder 用户头像
     * @return 返回信息
     */
    UserExecution addUser(LocalAuth localAuth, PersonInfo personInfo, ImageHolder imageHolder);

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return 是否成功登录
     */
    UserExecution loginUser(String username,String password);

    /**
     * 添加积分
     * @param personInfo 积分信息
     * @return
     */
    UserExecution addIntegral(PersonInfo personInfo);
}
