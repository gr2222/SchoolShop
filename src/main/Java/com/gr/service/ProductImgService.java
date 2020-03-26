package com.gr.service;

import com.gr.entity.ProductImg;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-20 11:41
 */
public interface ProductImgService {
    /**
     * 根据商品id查询商品图片
     * @param productId 商品id
     * @return
     */
    List<ProductImg> getProductImgList(Long productId);
}
