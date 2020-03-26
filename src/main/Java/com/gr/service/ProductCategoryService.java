package com.gr.service;

import com.gr.dto.ProductCategoryExecution;
import com.gr.entity.ProductCategory;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 14:52
 */
public interface ProductCategoryService {
    /**
     * 根据商铺ID 查找 该商铺的商品分类
     *
     * @param shopId 商铺ID
     * @return 商品分类列表
     */
    List<ProductCategory> getProductCategoryList(Long shopId);

    /**
     * 批量添加商品分类
     * @param list 商品分类信息表
     * @return 结果信息
     */
    ProductCategoryExecution batchAddProductCategory(List<ProductCategory> list);

    /**
     * 删除指定shopId和productCategoryId
     * @param productCategoryId productCategoryId
     * @param shopId shopId
     * @return 结果信息
     */
    ProductCategoryExecution deleteProductCategory(Long productCategoryId,Long shopId);
}
