package com.gr.dao;

import com.gr.entity.HeadLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-18 15:09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class HeadLineDaoTest {
    @Autowired
    HeadLineDao headLineDao;
    @Test
    public void queryHeadLine() {
        List<HeadLine> headLines = headLineDao.queryHeadLine(new HeadLine());
        for (int i = 0; i < headLines.size(); i++) {
            System.out.println(headLines.get(i));
        }
    }
}