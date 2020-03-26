package com.gr.web.order;

import com.gr.dto.OrderExecution;
import com.gr.dto.OrderSummaryExecution;
import com.gr.dto.UserExecution;
import com.gr.entity.Order;
import com.gr.entity.PersonInfo;
import com.gr.entity.Product;
import com.gr.entity.Shop;
import com.gr.enums.OrderSateEnum;
import com.gr.enums.UserStateEnum;
import com.gr.service.OrderService;
import com.gr.service.OrderSummaryService;
import com.gr.service.ProductService;
import com.gr.service.UserService;
import com.gr.util.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-22 20:29
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderSummaryService orderSummaryServicel;

    @RequestMapping("/addorder")
    @ResponseBody
    public Map<String, Object> addOrder(@RequestParam("productId") Long productId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("personInfoSession");
        Product productById = productService.getProductById(productId);
        if (productById == null) {
            map.put("success", true);
            map.put("errMsg", "没有该商品");
            return map;
        }
        Order order = new Order();
        order.setIntegral(productById.getIntegral());
        order.setPromotionPrice(productById.getPromotionPrice());
        order.setNormalPrice(productById.getNormalPrice());
        order.setShop(productById.getShop());
        order.setProduct(productById);
        order.setLastEditTime(new Date());
        order.setCreateTime(new Date());
        order.setEnableStatus(1);
        order.setBuyer(personInfo);
        Boolean aBoolean = orderService.addOrder(order);
        if (aBoolean) {
            map.put("success", true);
        } else {
            map.put("success", true);
            map.put("errMsg", "添加订单失败");
        }
        return map;
    }

    @RequestMapping("/getorderbybuyid")
    @ResponseBody
    public Map<String, Object> getOrderBybuyId(@RequestParam(value = "pageNum", required = false) int pageNum,
                                               HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("personInfoSession");
        Order order = new Order();
        order.setBuyer(personInfo);
        OrderExecution orderList = orderService.getOrderList(order, pageNum, 4);
        if (orderList.getState() == OrderSateEnum.SUCCESS.getState()) {
            map.put("success", true);
            map.put("orderList", orderList.getList());
            map.put("count", orderList.getCount());
        } else if (orderList.getState() == OrderSateEnum.DATA_NULL.getState()) {
            map.put("success", true);
            map.put("count", orderList.getCount());
            map.put("orderList", null);
        } else {
            map.put("success", false);
            map.put("errMsg", orderList.getStateInfo());
        }
        return map;
    }

    @RequestMapping("/setorderuuid")
    @ResponseBody
    public Map<String, Object> setOrderUuid(@RequestParam("orderId") Long orderId) {
        Map<String, Object> map = new HashMap<>(3);
        Long uuid = MathUtil.getUuid();
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderuuid(uuid);
        order.setLastEditTime(new Date());
        System.out.println(order);
        OrderExecution orderExecution = orderService.setOrderUuidAndEnableStatus(order);
        if (orderExecution.getState() == OrderSateEnum.SUCCESS.getState()) {
            map.put("success", true);
            map.put("orderuuid", uuid);
        } else {
            map.put("success", false);
            map.put("errMsg", orderExecution.getStateInfo());
        }
        return map;
    }

    @RequestMapping("/endorder")
    @ResponseBody
    public Map<String, Object> endOrder(@RequestParam("orderMsg") String orderMsg, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        String[] split = orderMsg.split("｜");
        Order orderinfo = new Order();
        orderinfo.setOrderId(Long.valueOf(split[0]));
        OrderExecution orderList = orderService.getOrderList(orderinfo, 0, 3);
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopListSession");
        if (shopList == null) {
            map.put("success", false);
            map.put("errMsg", "请先登录商铺管理系统");
            return map;
        }
        if (orderList.getState() == OrderSateEnum.SUCCESS.getState()) {
            Order order = orderList.getList().get(0);
            if (order.getEnableStatus() == 2) {
                map.put("success", false);
                map.put("errMsg", "该商品订单已被兑换");
                return map;
            }
            if (order.getOrderuuid().equals(Long.valueOf(split[1]))) {
                if (orderisshop(shopList, order)) {
                    order.setLastEditTime(new Date());
                    order.setEnableStatus(2);
                    OrderExecution orderExecution = orderService.setOrderUuidAndEnableStatus(order);
                    if (orderExecution.getState() == OrderSateEnum.SUCCESS.getState()) {
                        PersonInfo buyer = order.getBuyer();
                        buyer.setIntegral(order.getIntegral());
                        UserExecution userExecution = userService.addIntegral(buyer);
                        if (userExecution.getState() == UserStateEnum.SUCCESS.getState()) {
                            map.put("success", true);
                            map.put("msg", "操作成功");
                        } else {
                            map.put("success", true);
                            map.put("errmsg", "操作失败");
                        }
                    }
                } else {
                    map.put("success", false);
                    map.put("errMsg", "该商品你无权操作");
                }
            } else {
                map.put("success", false);
                map.put("errMsg", "二维码已过期");
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "没有该商品");
        }
        return map;
    }

    @RequestMapping("/ordersummarychart")
    @ResponseBody
    public Map<String, Object> orderSummaryChart(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        Shop shopSession = (Shop) request.getSession().getAttribute("shopSession");
        try {
            List<OrderSummaryExecution> orderSummary = orderSummaryServicel.getOrderSummary(shopSession.getShopId());
            map.put("success", true);
            map.put("orderSummary",orderSummary);
            return map;
        } catch (ParseException e) {
            map.put("success", false);
            map.put("errmMsg","错误");
            return map;
        }
    }
    private boolean orderisshop(List<Shop> shopList, Order order) {
        for (int i = 0; i < shopList.size(); i++) {
            if (shopList.get(i).getShopId().equals(order.getShop().getShopId())) {
                return true;
            }
        }
        return false;
    }
}
