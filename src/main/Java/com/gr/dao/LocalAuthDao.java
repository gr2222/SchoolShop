package com.gr.dao;

import com.gr.entity.LocalAuth;
import org.apache.ibatis.annotations.Param;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 13:51
 */
public interface LocalAuthDao {
    /**
     * 根据username和password查询用户
     * @param userName 用户名
     * @param password 密码
     * @return 返回用户
     */
    LocalAuth queryLocalAuthByUserNameAndPwd(@Param("userName")String userName,@Param("password") String password);

    /**
     * 插入用户（注册）
     * @param localAuth 用户信息
     * @return
     */
    int insertLocalAuth(LocalAuth localAuth);
}
