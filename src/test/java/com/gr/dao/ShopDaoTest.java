package com.gr.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.entity.Area;
import com.gr.entity.PersonInfo;
import com.gr.entity.Shop;
import com.gr.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 10:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ShopDaoTest {
    @Autowired
    private ShopDao shopDao;

    @Test
    public void insertShop() {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        ShopCategory shopCategory = new ShopCategory();
        personInfo.setUserId(1l);
        area.setAreaId(3);
        shopCategory.setShopCategoryId(1l);
        shop.setPersonInfo(personInfo);
        shop.setArea(area);
        shop.setShopCategory(shopCategory);
        shop.setShopName("一杯");
        shop.setPhone("123123123");
        shop.setPriority(1);
        shop.setShopImg("asdadadsd13213123");
        shop.setShopDesc("描述");
        shop.setShopAddr("后校门出去100m");
        shop.setCreateTime(new Date());
        shop.setLastEditTime(new Date());
        shop.setEnableStatus(2);
        shop.setAdvice("审核中");
        int i = shopDao.insertShop(shop);

    }

    @Test
    public void updateShop() {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        Area area = new Area();
        shop.setShopId(1l);
        shop.setShopDesc("test");
        shop.setShopAddr("test");
        int i = shopDao.updateShop(shop);
    }

    @Test
    public void queryByShopId() {
        Long shopId = 16L;
        Shop shop = shopDao.queryByShopId(shopId);
        System.out.println(shop);
    }

    @Test
    public void queryShops() {
        Shop shop = new Shop();
        ShopCategory shopCategory1 = new ShopCategory();
        shopCategory1.setShopCategoryId(15l);
        shop.setShopCategory(shopCategory1);
        List<Shop> shops = shopDao.queryShops(shop, 0, 1000);
        System.out.println(shops.size());
        for (int i = 0; i < shops.size(); i++) {
            System.out.println(shops.get(i)+"\n");
        }
    }

    @Test
    public void queryShopCount() {
        Shop shop = new Shop();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1l);
        shop.setEnableStatus(2);
        shop.setPersonInfo(personInfo);
        int i = shopDao.queryShopCount(shop);
        System.out.println(i);
    }
    @Test
    public void test(){
        String string = "{\n" +
                "    \"shopCategory\": {\n" +
                "        \"shopCategoryId\": 3\n" +
                "    }\n" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Shop shop = objectMapper.readValue(string, Shop.class);
            System.out.println(shop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}