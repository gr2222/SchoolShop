package com.gr.dao;

import com.gr.entity.Product;
import com.gr.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 09:50
 */
public interface ProductDao {
    /**
     * 插入商品
     *
     * @param product 商品
     * @return 影响行数
     */
    int insertProduct(Product product);

    /**
     * 根据商品id 查询商品信息
     *
     * @param productId
     * @return
     */
    Product queryProductById(Long productId);

    /**
     * 更新 商品属性
     * @param product 商品需要更新的属性
     * @return 影响的行数
     */
    int updateProduct(Product product);

    /**
     *
     分页查询商品列表，根据商品名、商品状态、商品类别 、所属商铺
     * @param productmsg 商品的信息
     * @param rowIndex 第几页
     * @param pageSize 每一页多少条
     * @return 商品列表
     */
    List<Product> queryShops(@Param("productmsg")Product productmsg, @Param("rowIndex")int rowIndex, @Param("pageSize")int pageSize);

    /**
     * 配合queryShops方法使用 得到总数
     * @param productmsg
     * @return
     */
    int queryShopCount(@Param("productmsg")Product productmsg);


    int updateProductCategoryToNull(Long productCategoryId);
}
