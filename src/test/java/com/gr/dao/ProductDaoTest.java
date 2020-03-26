package com.gr.dao;

import com.gr.entity.Product;
import com.gr.entity.ProductCategory;
import com.gr.entity.Shop;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ProductDaoTest {
    @Autowired
    ProductDao productDao;

    @Test
    public void insertProduct() {
        Product product = new Product();
        product.setProductName("好吃好看");
        product.setCreateTime(new Date());
        product.setEnableStatus(0);
        product.setProductDesc("1111111");
        product.setImgAddr("111111");
        product.setPromotionPrice("111");
        product.setLastEditTime(new Date());
        product.setNormalPrice("1000");
        product.setPriority(10);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(1l);
        product.setProductCategory(productCategory);
        Shop shop  = new Shop();
        shop.setShopId(7l);
        product.setShop(shop);
        System.out.println(product);
        int i = productDao.insertProduct(product);
        System.out.println(i);
    }

    @Test
    public void queryProductById() {
        Product product = productDao.queryProductById(63l);
        System.out.println(product);
    }

    @Test
    public void updateProduct() {

        Product product = new Product();
       product.setIntegral(3);
       product.setProductId(63l);
        int i = productDao.updateProduct(product);
        System.out.println(i);
    }

    @Test
    public void queryShops() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(16l);
        product.setShop(shop);
        List<Product> shops = productDao.queryShops(product, 0, 100);
        for (int i = 0; i < shops.size(); i++) {
            System.out.println(shops.get(i));
        }
    }

    @Test
    public void queryShopCount() {
        Product product = new Product();
        Shop shop = new Shop();
        shop.setShopId(16l);
        product.setShop(shop);
        int i = productDao.queryShopCount(product);
        System.out.println(i);
    }
}