package com.gr.dao;

import com.gr.entity.Product;
import com.gr.entity.ProductImg;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:00
 */
public interface ProductImgDao {
    /**
     * 批量添加详情图
     * @param list
     * @return
     */
    int batchInsertProductImg(List<ProductImg> list);

    /**
     * 根据商品id查询图片(支持多个)
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgs(Long productId);

    /**
     * 根据商品id删除图片(支持多个)
     * @param productId
     * @return
     */
    int deleteProductImgs(Long productId);
}
