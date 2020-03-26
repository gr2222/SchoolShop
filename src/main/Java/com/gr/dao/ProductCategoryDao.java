package com.gr.dao;

import com.gr.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 14:37
 */

public interface ProductCategoryDao {

    /**
     * 根据商铺id 获取 商品分类
     *
     * @param shopId
     * @return 商品分类列表
     */
    List<ProductCategory> getProductCategoryList(Long shopId);

    /**
     * 批量添加分类
     *
     * @param list 需要添加的商品表
     * @return 影响了多少行
     */
    int batchInsertProductCategory(List<ProductCategory> list);

    /**
     * 删除 商品分类信息
     *
     * @param productCategoryId 商品分类id
     * @param shopId 商铺id
     * @return 影响行数
     */
    int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long shopId);
}
