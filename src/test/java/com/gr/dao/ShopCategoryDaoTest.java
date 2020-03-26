package com.gr.dao;

import com.gr.entity.ShopCategory;
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
 * @date 2020-02-14 15:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ShopCategoryDaoTest {
    @Autowired
    private ShopCategoryDao shopCategoryDao;

    @Test
    public void queryShopCategory() {
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(1l);
        List<ShopCategory> shopCategories = shopCategoryDao.queryShopCategory(null);
        for (ShopCategory s : shopCategories) {
            System.out.println(s);
        }
    }
}