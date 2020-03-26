package com.gr.service.impl;

import com.gr.dto.ImageHolder;
import com.gr.dto.ShopExecution;
import com.gr.entity.Area;
import com.gr.entity.PersonInfo;
import com.gr.entity.Shop;
import com.gr.entity.ShopCategory;
import com.gr.enums.ShopSateEnum;
import com.gr.service.ShopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 21:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ShopServiceImplTest {
    @Autowired
    private ShopService shopService;
    @Test
    public void addShop() throws FileNotFoundException {
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
        shop.setShopDesc("描述");
        shop.setShopAddr("后校门出去100m");
        shop.setEnableStatus(ShopSateEnum.REVIEW.getState());
        shop.setAdvice("审核中");
        File file = new File("/Users/gaorui/Downloads/image/nvren.jpeg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(), inputStream);
        ShopExecution shopExecution = shopService.addShop(shop, imageHolder);
    }

    @Test
    public void upDateShop() throws FileNotFoundException {
        Shop shop = new Shop();
        shop.setShopId(7l);
        shop.setShopName("于凌饮品");
        shop.setPhone("撒旦为2323232");
        shop.setPriority(1);
        shop.setShopDesc("test");
        shop.setShopAddr("tetststtsts");
        shop.setEnableStatus(ShopSateEnum.REVIEW.getState());
        shop.setAdvice("审核中");
        File file = new File("/Users/gaorui/壁纸/wallhaven-ox678m.jpg");
        InputStream inputStream = new FileInputStream(file);
        ImageHolder imageHolder = new ImageHolder(file.getName(), inputStream);
        shopService.updateShop(shop,imageHolder);
    }

    @Test
    public void getShopList() {
        Shop shop = new Shop();
        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(3l);
        shop.setShopCategory(shopCategory);
        ShopExecution shopList = shopService.getShopList(shop, 2, 3);
        if (shopList.getState()==ShopSateEnum.SUCCESS.getState()){
            System.out.println("查询总数为："+shopList.getCount());
            List<Shop> shops = shopList.getShopList();
            for (int i = 0; i < shops.size(); i++) {
                System.out.println(shops.get(i));
            }
        }

    }
}