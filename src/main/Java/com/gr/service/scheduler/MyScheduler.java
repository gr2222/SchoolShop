package com.gr.service.scheduler;

import com.gr.dao.OrderDao;
import com.gr.dao.OrderSummaryDao;
import com.gr.dao.ProductDao;
import com.gr.dao.ShopDao;
import com.gr.dto.OrderExecution;
import com.gr.dto.ShopExecution;
import com.gr.entity.Order;
import com.gr.entity.OrderSummary;
import com.gr.entity.Product;
import com.gr.entity.Shop;
import com.gr.service.OrderService;
import com.gr.service.ProductService;
import com.gr.service.ShopService;
import com.gr.util.TimeUtil;
import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-24 18:43
 */
@Component
public class MyScheduler {
    @Autowired
    ShopService shopService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductDao productDao;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderDao orderDao;
    @Autowired
    OrderSummaryDao orderSummaryDao;

    @Scheduled(cron = "0 0 1 * * ?")
    public void orderSummaryontime() {
        System.out.println("开始汇总");
        Order order = new Order();
        try {
            order.setLastEditTime(TimeUtil.oldtime(1));
            int i = productDao.queryShopCount(new Product());
            List<Product> products = productDao.queryShops(new Product(), 0, i);
            for (int j = 0; j < products.size(); j++) {
                order.setProduct(products.get(j));
                order.setEnableStatus(2);
                int i1 = orderDao.queryOrderCount(order);
                OrderSummary orderSummary = new OrderSummary();
                orderSummary.setOrderNum(Integer.toUnsignedLong(i1));
                Product product = productDao.queryProductById(order.getProduct().getProductId());
                orderSummary.setProduct(product);
                orderSummary.setShopId(product.getShop().getShopId());
                orderSummary.setCreateTime(TimeUtil.oldtime(1));
                orderSummaryDao.insertOrderSummary(orderSummary);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("结束汇总");
    }


}
