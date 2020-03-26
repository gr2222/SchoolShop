package com.gr.interceptor.shopadmin;

import com.gr.entity.Shop;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 21:57
 */
public class ShopPermissionInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Shop shop = (Shop) request.getSession().getAttribute("shopSession");
        List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopListSession");
        if (shop!=null && shopList!=null){
            for (Shop s:shopList){
                if (s.getShopId().equals(shop.getShopId())){
                    return true;
                }
            }
        }
        return false;
    }
}
