package com.gr.service.impl;

import com.gr.dto.ImageHolder;
import com.gr.dto.ProductExecution;
import com.gr.entity.Product;
import com.gr.entity.ProductCategory;
import com.gr.entity.Shop;
import com.gr.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 11:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ProductServiceImplTest {
    @Autowired
    ProductService productService;

    @Test
    public void addProduct() throws FileNotFoundException {
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
        productCategory.setProductCategoryId(22l);
        product.setProductCategory(productCategory);
        Shop shop  = new Shop();
        shop.setShopId(16l);
        product.setShop(shop);
        File file = new File("/Users/gaorui/壁纸/wallhaven-lq6rmy.png");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(),inputStream);

        List<ImageHolder> list = new ArrayList<>();
        File file1 = new File("/Users/gaorui/壁纸/wallhaven-73w51y.jpg");
        InputStream inputStream1 = new FileInputStream(file1);
        ImageHolder imageHolder1 = new ImageHolder(file1.getName(),inputStream1);
        File file2 = new File("/Users/gaorui/壁纸/wallhaven-73w51y.jpg");
        InputStream inputStream2 = new FileInputStream(file2);
        ImageHolder imageHolder2 = new ImageHolder(file2.getName(),inputStream2);
        list.add(imageHolder1);
        list.add(imageHolder2);
        ProductExecution productExecution = productService.addProduct(product, imageHolder, list);

     }

    @Test
    public void updataProduct() throws FileNotFoundException {
        Product product = new Product();
        product.setProductId(41l);
        product.setProductName("好吃好看111111");
        product.setCreateTime(new Date());
        product.setEnableStatus(0);
        product.setProductDesc("1111111111111");
        product.setImgAddr("1111111111111");
        product.setPromotionPrice("1111111111");
        product.setLastEditTime(new Date());
        product.setNormalPrice("1000");
        product.setPriority(10);
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryId(22l);
        product.setProductCategory(productCategory);
        File file = new File("/Users/gaorui/Downloads/image/nvren.jpeg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(),inputStream);

        List<ImageHolder> list = new ArrayList<>();
        File file1 = new File("/Users/gaorui/Downloads/image/timg.jpg");
        InputStream inputStream1 = new FileInputStream(file1);
        ImageHolder imageHolder1 = new ImageHolder(file1.getName(),inputStream1);
        File file2 = new File("/Users/gaorui/Downloads/image/nvren.jpeg");
        InputStream inputStream2 = new FileInputStream(file2);
        ImageHolder imageHolder2 = new ImageHolder(file2.getName(),inputStream2);
        list.add(imageHolder1);
        list.add(imageHolder2);
        ProductExecution productExecution = productService.updataProduct(product, imageHolder, list);
    }
}