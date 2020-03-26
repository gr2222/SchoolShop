package com.gr.dao;

import com.gr.entity.LocalAuth;
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
 * @date 2020-02-21 14:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class LocalAuthDaoTest {
    @Autowired
    LocalAuthDao localAuthDao;

    @Test
    public void queryLocalAuthByUserNameAndPwd() {
        LocalAuth localAuth1 = localAuthDao.queryLocalAuthByUserNameAndPwd("maotentai", "123123");
        System.out.println(localAuth1);
    }

    @Test
    public void insertLocalAuth() {
        LocalAuth localAuth = new LocalAuth();
        localAuth.setCreateTime(new Date());
        localAuth.setLastEditTime(new Date());
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1l);
        localAuth.setPersonInfo(personInfo);
        localAuth.setUsername("小绵羊");
        localAuth.setPassword("123123");
        localAuthDao.insertLocalAuth(localAuth);
        LocalAuth localAuth1 = localAuthDao.queryLocalAuthByUserNameAndPwd("小绵羊", "123123");
        System.out.println(localAuth1);
    }
}