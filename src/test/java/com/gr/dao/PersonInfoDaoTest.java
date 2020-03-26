package com.gr.dao;

import com.gr.entity.PersonInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 14:37
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class PersonInfoDaoTest {
    @Autowired
    PersonInfoDao personInfoDao;

    @Test
    public void insertPersonInfo() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setName("小小妞");
        personInfo.setCreateTime(new Date());
        personInfo.setEmail("7860123123123@qq.com");
        personInfo.setEnableStatus(1);
        personInfo.setGender("男");
        personInfo.setLastEditTime(new Date());
        personInfo.setProfileImg("123123123");
        personInfo.setUserType(1);
        personInfoDao.insertPersonInfo(personInfo);
        System.out.println(personInfo.getUserId());
    }

    @Test
    public void addIntegral() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(9l);
        personInfo.setIntegral(12);
        int i = personInfoDao.addIntegral(personInfo);
        System.out.println(i);
    }
}