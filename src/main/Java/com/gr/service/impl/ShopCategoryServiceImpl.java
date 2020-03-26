package com.gr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.Exceptions.ShopCategoryException;
import com.gr.cache.JedisUtil;
import com.gr.dao.ShopCategoryDao;
import com.gr.entity.Shop;
import com.gr.entity.ShopCategory;
import com.gr.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-14 16:08
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {
    @Autowired
    private ShopCategoryDao shopCategoryDao;
    @Autowired
    JedisUtil.Keys jedisKeys;
    @Autowired
    JedisUtil.Strings jedisStrings;


    @Override
    @Transactional
    public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
        String key = SHOPCATEGORYLSIT;
        if (shopCategory != null && shopCategory.getShopCategoryId() != null) {
            key = key + "_parent" + shopCategory.getShopCategoryId();
        }
        if (shopCategory != null && shopCategory.getShopCategoryId() == null) {
            key = key + "_notnull";
        }
        ObjectMapper mapper = new ObjectMapper();
        List<ShopCategory> shopCategories = null;
        if (!jedisKeys.exists(key)) {
            shopCategories = shopCategoryDao.queryShopCategory(shopCategory);
            String shopCategoryListStr =null;
            try {
                shopCategoryListStr = mapper.writeValueAsString(shopCategories);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw  new ShopCategoryException(e.getMessage());
            }
            jedisStrings.set(key, shopCategoryListStr);
        }else {
            String shopCategoryListStr = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, ShopCategory.class);
            try {
                shopCategories = mapper.readValue(shopCategoryListStr, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                throw  new ShopCategoryException(e.getMessage());
            }
        }
        return shopCategories;
    }
}
