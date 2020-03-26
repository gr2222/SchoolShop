package com.gr.service.impl;

import com.gr.Exceptions.ProductCateforyException;
import com.gr.dao.ProductCategoryDao;
import com.gr.dao.ProductDao;
import com.gr.dto.ProductCategoryExecution;
import com.gr.entity.ProductCategory;
import com.gr.enums.ProductCategoryEnum;
import com.gr.service.ProductCategoryService;
import com.gr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 14:52
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    ProductCategoryDao productCategoryDao;
    @Autowired
    ProductDao productDao;

    @Override
    public List<ProductCategory> getProductCategoryList(Long shopId) {
        return productCategoryDao.getProductCategoryList(shopId);
    }

    @Override
    public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> list) {
        ProductCategoryExecution productCategoryExecution;
        if (list.size() <= 0) {
            return new ProductCategoryExecution(ProductCategoryEnum.EMPTY_LIST);
        }
        try {
            productCategoryDao.batchInsertProductCategory(list);
            productCategoryExecution = new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
        } catch (Exception e) {
            productCategoryExecution = new ProductCategoryExecution(ProductCategoryEnum.INNER_ERROR);
        }
        return productCategoryExecution;
    }

    @Override
    @Transactional
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId, Long shopId) throws ProductCateforyException {
        ProductCategoryExecution productCategoryExecution = null;
        try {
            int i2 = productDao.updateProductCategoryToNull(productCategoryId);
            if (i2 < 0) {
                throw new ProductCateforyException("删除失败");
            }
            int i = productCategoryDao.deleteProductCategory(productCategoryId, shopId);
            if (i <= 0) {
                throw new ProductCateforyException("删除失败");
            } else {
                productCategoryExecution = new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
                return productCategoryExecution;
            }
        } catch (Exception e) {
            throw new ProductCateforyException("删除失败");
        }
    }
}
