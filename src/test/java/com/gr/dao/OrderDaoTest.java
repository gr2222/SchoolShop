package com.gr.dao;

import com.gr.entity.Order;
import com.gr.entity.PersonInfo;
import com.gr.entity.Product;
import com.gr.entity.Shop;
import com.gr.util.ImageUtil;
import com.gr.util.MathUtil;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 20:15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class OrderDaoTest {
    @Autowired
    OrderDao orderDao;
    @Test
    public void insertOrder() {
        Order order = new Order();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(2l);
        order.setBuyer(personInfo);
        order.setEnableStatus(1);
        order.setCreateTime(new Date());
        order.setLastEditTime(new Date());
        order.setSellerId(1l);
        Shop shop = new Shop();
        shop.setShopId(18l);
        order.setShop(shop);
        Product product = new Product();
        product.setProductId(43l);
        order.setProduct(product);
        order.setNormalPrice("18");
        order.setPromotionPrice("25");
        order.setIntegral(2);
        int i = orderDao.insertOrder(order);
        System.out.println(i);
        System.out.println(order.getOrderId());
    }

    @Test
    public void queryOrder() {
        Order order = new Order();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(9l);
        order.setBuyer(personInfo);
        List<Order> orders = orderDao.queryOrder(order, 0, 3);
        System.out.println(orders.size());
        for (int i = 0; i < orders.size(); i++) {
            System.out.println(orders.get(i));
        }
    }

    @Test
    public void queryOrderCount() {
        Order order = new Order();
        Product product = new Product();
        product.setProductId(43l);
        order.setProduct(product);
        order.setEnableStatus(2);
        int i = orderDao.queryOrderCount(order);
        System.out.println(i);
    }

    @Test
    public void updateOrder() {
        Long orderId =12l;
        Long uuid = MathUtil.getUuid();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderuuid(uuid);
        order.setLastEditTime(new Date());
        int i = orderDao.updateOrder(order);
        System.out.println(i);
    }
}