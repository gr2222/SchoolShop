package com.gr.dao;

import com.gr.entity.OrderSummary;
import com.gr.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-24 20:20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class OrderSummaryDaoTest {
    @Autowired
    OrderSummaryDao orderSummaryDao;


    @Test
    public void selectOrderSummary() throws ParseException {
        List<OrderSummary> orderSummaries = orderSummaryDao.selectOrderSummary(18l, TimeUtil.oldtime(2));
        for (int i = 0; i < orderSummaries.size(); i++) {
            System.out.println(orderSummaries.get(i));
        }
    }


}