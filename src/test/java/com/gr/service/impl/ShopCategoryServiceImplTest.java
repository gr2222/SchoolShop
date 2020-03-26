package com.gr.service.impl;

import com.gr.entity.ShopCategory;
import com.gr.service.ShopCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 13:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml", "classpath:spring/spring-redis.xml"})
public class ShopCategoryServiceImplTest {
    @Autowired
    ShopCategoryService shopCategoryService;

    @Test
    public void getShopCategoryList() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategoryService.getShopCategoryList(null);
    }
}