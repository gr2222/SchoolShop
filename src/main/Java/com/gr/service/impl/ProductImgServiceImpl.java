package com.gr.service.impl;

import com.gr.dao.ProductImgDao;
import com.gr.entity.ProductImg;
import com.gr.service.ProductImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-20 11:44
 */
@Service
public class ProductImgServiceImpl implements ProductImgService {
    @Autowired
    ProductImgDao productImgDao;

    @Override
    public List<ProductImg> getProductImgList(Long productId) {
        return productImgDao.queryProductImgs(productId);
    }
}
