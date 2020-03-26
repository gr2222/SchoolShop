package com.gr.service.scheduler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-25 10:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml","classpath:spring/spring-redis.xml"})
public class mySchedulerTest {
    @Autowired
    MyScheduler myScheduler;

    @Test
    public void orderSummaryontime() {
        myScheduler.orderSummaryontime();
    }
}