package com.gr.dao;

import com.gr.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-14 15:14
 */
public interface ShopCategoryDao {
    /**
     * 获取店铺分类信息
     * @param shopCategoryBasis 根据某一分类找到子分类
     * @return 店铺分类信息列表
     */
    List<ShopCategory> queryShopCategory(@Param("shopCategoryBasis")ShopCategory shopCategoryBasis);
}
