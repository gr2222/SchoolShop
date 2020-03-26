package com.gr.service.impl;

import com.gr.Exceptions.ProductException;
import com.gr.dao.ProductDao;
import com.gr.dao.ProductImgDao;
import com.gr.dto.ImageHolder;
import com.gr.dto.ProductExecution;
import com.gr.entity.Product;
import com.gr.entity.ProductImg;
import com.gr.entity.Shop;
import com.gr.enums.ProductSateEnum;
import com.gr.service.ProductService;
import com.gr.util.ImageUtil;
import com.gr.util.PageMath;
import com.gr.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-17 10:46
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductImgDao productImgDao;

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder image, List<ImageHolder> productImgs) throws ProductException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            if (product.getProductCategory() != null && product.getProductCategory().getProductCategoryId() != null) {
                product.setCreateTime(new Date());
                product.setLastEditTime(new Date());
                product.setEnableStatus(1);
                if (image != null) {
                    addProductImage(product, image);
                } else {
                    throw new ProductException("请添加缩略图");
                }
                try {
                    int i = productDao.insertProduct(product);
                    if (i <= 0) {
                        throw new ProductException("创建商品失败");
                    }
                } catch (Exception e) {
                    throw new ProductException("创建商品失败" + e.getMessage());
                }
                if (productImgs != null && productImgs.size() > 0) {
                    addProductImageList(product, productImgs);
                }
                return new ProductExecution(ProductSateEnum.SUCCESS);

            } else {
                throw new ProductException("需要填写分类信息");
            }
        } else {
            throw new ProductException("权限验证不对");
        }
    }

    @Override
    public Product getProductById(Long product) {
        return productDao.queryProductById(product);
    }

    @Override
    @Transactional
    public ProductExecution updataProduct(Product product, ImageHolder image, List<ImageHolder> productImgs) throws ProductException {
        if (product == null || product.getProductId() == null) {
            return new ProductExecution(ProductSateEnum.DATA_ERROR);
        }
        product.setLastEditTime(new Date());
        if (image != null) {
            Product productOfDatabase = productDao.queryProductById(product.getProductId());
            product.setShop(productOfDatabase.getShop());
            //删除以前的图片
            ImageUtil.deleteFileOrPath(productOfDatabase.getImgAddr());
            //持久化现在的图片
            addProductImage(product, image);
        }
        if (productImgs.size() > 0) {
            List<ProductImg> list = productImgDao.queryProductImgs(product.getProductId());
            try {
                for (int i = 0; i < list.size(); i++) {
                    ImageUtil.deleteFileOrPath(list.get(i).getImgAddr());
                }
            } catch (Exception e){
                throw new ProductException("更新错误");
            }
            productImgDao.deleteProductImgs(product.getProductId());
            addProductImageList(product, productImgs);
        }
        try {
            productDao.updateProduct(product);
        } catch (Exception e) {
            throw new ProductException("更新错误"+e.getMessage());
        }
        return new ProductExecution(ProductSateEnum.SUCCESS);
    }

    @Override
    public ProductExecution getProductList(Product product, int pageNum, int pageSize) {
        if (product==null){
            return new ProductExecution(ProductSateEnum.DATA_ERROR);
        }
        int rowIndex = PageMath.pageNumtoRowIndex(pageNum, pageSize);
        List<Product> products = productDao.queryShops(product, rowIndex, pageSize);
        int i = productDao.queryShopCount(product);
        if (i==0){
            return  new ProductExecution(ProductSateEnum.DATA_NULL);
        }
        if (i<0){
            return new ProductExecution(ProductSateEnum.INNER_ERR);
        }
        ProductExecution productExecution = new ProductExecution(ProductSateEnum.SUCCESS);
        productExecution.setList(products);
        productExecution.setCount(i);

        return productExecution;
    }


    /**
     * 将商品详细图持久化
     *
     * @param product     商品信息
     * @param productImgs 详细图列表
     */
    private void addProductImageList(Product product, List<ImageHolder> productImgs) throws ProductException {
        String shopImgPath = PathUtil.getShopImgPath(product.getShop().getShopId());
        List<ProductImg> listObj = new ArrayList<>();
        for (int i = 0; i < productImgs.size(); i++) {
            String imgAddr = ImageUtil.makeProductDetailImg(productImgs.get(i), shopImgPath);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImg.setPriority(1);
            listObj.add(productImg);
        }
        if (listObj.size() > 0) {
            try {
                int i = productImgDao.batchInsertProductImg(listObj);
                if (i <= 0) {
                    throw new ProductException("添加详情图片失败");
                }
            } catch (Exception e) {
                throw new ProductException("添加详情图片失败" + e.getMessage());
            }
        }
    }

    /**
     * 将商品缩略图持久化
     *
     * @param product 商品信息
     * @param image   缩略图
     */
    private void addProductImage(Product product, ImageHolder image) {
        String shopImgPath = PathUtil.getShopImgPath(product.getShop().getShopId());
        String s = ImageUtil.makingThumbnails(image, shopImgPath);
        product.setImgAddr(s);
    }


}
