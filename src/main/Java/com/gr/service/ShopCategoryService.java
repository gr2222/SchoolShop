package com.gr.service;

import com.gr.entity.ShopCategory;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-14 16:06
 */
public interface ShopCategoryService {
     String SHOPCATEGORYLSIT = "shopCategoryList";
    /**
     * 获取店铺分类信息
     * @param shopCategory 根据某一分类找到子分类
     * @return 店铺分类信息列表
     */
    List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
