package com.gr.dao;

import com.gr.entity.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 14:44
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ProductCategoryDaoTest {
    @Autowired
    ProductCategoryDao productCategoryDao;

    @Test
    public void getProductCategoryList() {
        List<ProductCategory> productCategorys = this.productCategoryDao.getProductCategoryList(7L);
        for (int i = 0; i < productCategorys.size(); i++) {
            System.out.println(productCategorys.get(i));
        }
    }

    @Test
    public void batchInsertProductCategory() {
        List<ProductCategory> list = new ArrayList<>();
        ProductCategory productCategory1 = new ProductCategory();
        productCategory1.setShopId(1L);
        productCategory1.setCreateTime(new Date());
        productCategory1.setPriority(10);
        productCategory1.setProductCategoryName("实例1");
        list.add(productCategory1);
        ProductCategory productCategory2 = new ProductCategory();
        productCategory2.setShopId(1L);
        productCategory2.setCreateTime(new Date());
        productCategory2.setPriority(10);
        productCategory2.setProductCategoryName("实例2");
        list.add(productCategory2);
        ProductCategory productCategory3 = new ProductCategory();
        productCategory3.setShopId(1L);
        productCategory3.setCreateTime(new Date());
        productCategory3.setPriority(10);
        productCategory3.setProductCategoryName("实例3");
        list.add(productCategory3);
        int i = productCategoryDao.batchInsertProductCategory(list);
        System.out.println(i);
    }

    @Test
    public void deleteProductCategory() {
        int i = productCategoryDao.deleteProductCategory(15l,1l);
        System.out.println(i);
    }
}