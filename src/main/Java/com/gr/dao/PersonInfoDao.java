package com.gr.dao;

import com.gr.entity.PersonInfo;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 14:29
 */
public interface PersonInfoDao {
    /**
     * 插入用户信息
     * @param personInfo 用户信息
     * @return 影响行数
     */
    int insertPersonInfo(PersonInfo personInfo);

    /**
     * 增加积分
     * @param personInfo
     * @return
     */
    int addIntegral(PersonInfo personInfo);
}
