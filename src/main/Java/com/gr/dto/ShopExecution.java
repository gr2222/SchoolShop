package com.gr.dto;

import com.gr.entity.Shop;
import com.gr.enums.ShopSateEnum;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 20:49
 */
public class ShopExecution {
    private Shop shop;

    private int state;

    private String stateInfo;

    private int count;

    private List<Shop> shopList;

    public ShopExecution() {
    }

    public ShopExecution(ShopSateEnum shopSate) {
        this.state = shopSate.getState();
        this.stateInfo = shopSate.getStateInfo();
    }

    public ShopExecution(ShopSateEnum shopSate, Shop shop) {
        this.state = shopSate.getState();
        this.stateInfo = shopSate.getStateInfo();
        this.shop = shop;
    }

    public ShopExecution(ShopSateEnum shopSate, List<Shop> shops) {
        this.state = shopSate.getState();
        this.stateInfo = shopSate.getStateInfo();
        this.shopList = shops;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }
}
