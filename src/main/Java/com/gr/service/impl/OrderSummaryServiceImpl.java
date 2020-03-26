package com.gr.service.impl;

import com.gr.dao.OrderSummaryDao;
import com.gr.dao.ProductDao;
import com.gr.dto.OrderSummaryExecution;
import com.gr.entity.OrderSummary;
import com.gr.entity.Product;
import com.gr.entity.Shop;
import com.gr.service.OrderSummaryService;
import com.gr.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-25 12:48
 */
@Service
public class OrderSummaryServiceImpl implements OrderSummaryService {
    @Autowired
    OrderSummaryDao orderSummaryDao;
    @Autowired
    ProductDao productDao;

    @Override
    public List<OrderSummaryExecution> getOrderSummary(Long shopId) throws ParseException {
        List<OrderSummaryExecution> list = new ArrayList<>();
        List<OrderSummary> orderSummarys = orderSummaryDao.selectOrderSummary(shopId, TimeUtil.oldtime(1));
        List<OrderSummary> orderSummaryslast = orderSummaryDao.selectOrderSummary(shopId, TimeUtil.oldtime(2));
        for (int i = 0; i < orderSummarys.size(); i++) {
            OrderSummary orderSummary = orderSummarys.get(i);
            for (int j = 0; j < orderSummaryslast.size(); j++) {
                OrderSummary orderSummary1 = orderSummaryslast.get(j);
                if (orderSummary.getProduct().getProductId().equals(orderSummary1.getProduct().getProductId())){
                    OrderSummaryExecution orderSummaryExecution = new OrderSummaryExecution();
                    orderSummaryExecution.setProductName(orderSummary.getProduct().getProductName());
                    orderSummaryExecution.setTerdayOrderNum(orderSummary.getOrderNum());
                    orderSummaryExecution.setYeterdayOrderNum(orderSummary1.getOrderNum());
                    list.add(orderSummaryExecution);
                }
            }
        }
        return list;
    }
}
