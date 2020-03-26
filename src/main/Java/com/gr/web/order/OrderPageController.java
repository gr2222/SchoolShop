package com.gr.web.order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 20:29
 */
@Controller
@RequestMapping("/order")
public class OrderPageController {
    @RequestMapping(value = "/orderpage", method = RequestMethod.GET)
    public String orderPage() {
        return "order/orderpage";
    }
    @RequestMapping(value = "/orderdetail", method = RequestMethod.GET)
    public String orderDetail() {
        return "order/oderdetail";
    }
    @RequestMapping(value = "/ordermsg", method = RequestMethod.GET)
    public String orderMsg() {
        return "order/ordermsg";
    }
    //orderSummary.html

    @RequestMapping(value = "/ordersummary", method = RequestMethod.GET)
    public String orderSummary() {
        return "order/orderSummary";
    }
}
