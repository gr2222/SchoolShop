package com.gr.service;

import com.gr.Exceptions.ShopException;
import com.gr.dto.ImageHolder;
import com.gr.dto.ShopExecution;
import com.gr.entity.Shop;

import java.io.InputStream;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 21:14
 */
public interface  ShopService {
    /**
     * 添加商铺信息 包括图片的处理
     * @param shop 商户信息
     * @param imageHolder 图片流
     * @return 结果
     * @throws ShopException
     */
    ShopExecution addShop(Shop shop, ImageHolder imageHolder) throws ShopException;

    /**
     * 根据shopId 得到商铺信息
     * @param id shopId
     * @return 商铺信息
     */
    Shop getByShopId(Long id);

    /**
     * 修改商铺信息 包括图片的处理
     * @param shop 商户信息
     * @param imageHolder 图片流
     * @return 结果
     * @throws ShopException
     */
    ShopExecution updateShop(Shop shop , ImageHolder imageHolder)throws ShopException;

    /**
     * 分页查询Shop
     * @param shop 查询需要的信息
     * @param pageNum 查询第几页
     * @param pageSize 每页几个元素
     * @return 结果
     */
    ShopExecution getShopList(Shop shop, int pageNum,int pageSize);
}
