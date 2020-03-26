package com.gr.service;

import com.gr.Exceptions.ProductException;
import com.gr.dto.ImageHolder;
import com.gr.dto.ProductExecution;
import com.gr.entity.Product;
import com.gr.entity.ProductImg;

import java.io.InputStream;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:41
 */
public interface ProductService {
    /**
     * 添加商品信息和图片处理
     * @param product 商品信息
     * @param image 缩略图
     * @param productImgs 详细图列表
     * @return 结果信息
     * @throws ProductException 事务控制
     */
    ProductExecution addProduct(Product product, ImageHolder image, List<ImageHolder> productImgs) throws ProductException;


    /**
     * 通过id 获取 product
     * @param product id
     * @return product
     */
    Product getProductById(Long product);

    /**
     * 更新商品
     * @param product    商品信息
     * @param image 商品缩略图
     * @param productImgs 商品详细图
     * @return 返回信息
     * @throws ProductException
     */
    ProductExecution updataProduct(Product product, ImageHolder image, List<ImageHolder> productImgs) throws ProductException;

    /**
     * 获取 商品列表
     * @param product 商品信息
     * @param pageNum 页码
     * @param pageSize 每一页有多少个
     * @return 返回信息
     */
    ProductExecution getProductList(Product product,int pageNum,int pageSize);
}
