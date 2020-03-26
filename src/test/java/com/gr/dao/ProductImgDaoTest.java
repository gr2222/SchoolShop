package com.gr.dao;

import com.gr.entity.Product;
import com.gr.entity.ProductImg;
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
 * @date 2020-02-17 10:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class ProductImgDaoTest {
    @Autowired
    ProductImgDao productImgDao;

    @Test
    public void batchInsertProductImg() {
        ProductImg productImg = new ProductImg();
        productImg.setCreateTime(new Date());
        productImg.setImgAddr("11111");
        productImg.setImgDesc("11111");
        productImg.setPriority(1);
        productImg.setProductId(1l);
        ProductImg productImg2 = new ProductImg();
        productImg2.setCreateTime(new Date());
        productImg2.setImgAddr("11111");
        productImg2.setImgDesc("11111");
        productImg2.setPriority(1);
        productImg2.setProductId(1l);
        ProductImg productImg3 = new ProductImg();
        productImg3.setCreateTime(new Date());
        productImg3.setImgAddr("11111");
        productImg3.setImgDesc("11111");
        productImg3.setPriority(1);
        productImg3.setProductId(1l);

        List<ProductImg> list = new ArrayList<>();
        list.add(productImg);
        list.add(productImg2);
        list.add(productImg3);

        int i = productImgDao.batchInsertProductImg(list);
        System.out.println(i);
    }

    @Test
    public void queryProductImgs() {
        List<ProductImg> list = productImgDao.queryProductImgs(39l);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    @Test
    public void deleteProductImgs() {
        productImgDao.deleteProductImgs(39l);
    }
}