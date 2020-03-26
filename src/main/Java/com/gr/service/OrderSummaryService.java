package com.gr.service;

import com.gr.dto.OrderSummaryExecution;
import com.gr.entity.OrderSummary;

import java.text.ParseException;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-25 12:28
 */

public interface OrderSummaryService {
    List<OrderSummaryExecution> getOrderSummary(Long shopId) throws ParseException;

}
