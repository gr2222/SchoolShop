package com.gr.service.impl;

import com.gr.Exceptions.ShopException;
import com.gr.dao.ShopDao;
import com.gr.dto.ImageHolder;
import com.gr.dto.ShopExecution;
import com.gr.entity.Shop;
import com.gr.enums.ShopSateEnum;
import com.gr.service.ShopService;
import com.gr.util.ImageUtil;
import com.gr.util.PageMath;
import com.gr.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 21:16
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder imageHolder) throws ShopException {
        String fileName = imageHolder.getImageName();
        InputStream shopInputStream = imageHolder.getImage();
        if (shop == null) {
            return new ShopExecution(ShopSateEnum.NOT_SHOP);
        }
        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());
            int afterNumIn = shopDao.insertShop(shop);
            if (afterNumIn <= 0) {
                throw new ShopException("店铺信息持久话失败");
            }
            if (shopInputStream != null) {
                try {
                    ImageHolder imageHolder1 = new ImageHolder(fileName, shopInputStream);
                    addShopImage(shop, imageHolder1);
                } catch (Exception e) {
                    throw new ShopException("添加照片失败");
                }
                int afterNumUp = shopDao.updateShop(shop);
                if (afterNumUp <= 0) {
                    throw new ShopException("更新图片地址失败");
                }
            }
        } catch (Exception e) {
            throw new ShopException("添加店铺失败：" + e.getMessage());
        }
        return new ShopExecution(ShopSateEnum.REVIEW, shop);
    }

    @Override
    public Shop getByShopId(Long id) {
        return shopDao.queryByShopId(id);
    }

    @Override
    @Transactional
    public ShopExecution updateShop(Shop shop, ImageHolder imageHolder) throws ShopException {
        String fileName = imageHolder.getImageName();
        InputStream image = imageHolder.getImage();
        if (shop == null) {
            return new ShopExecution(ShopSateEnum.NOT_SHOP);
        } else {
            if (image != null && fileName != null) {
                Shop shop1 = shopDao.queryByShopId(shop.getShopId());
                if (shop1.getShopImg() != null) {
                    try {
                        ImageUtil.deleteFileOrPath(shop1.getShopImg());
                    } catch (Exception e) {
                        throw new ShopException("图片删除失败");
                    }
                }
                try {
                    ImageHolder imageHolder1 = new ImageHolder(fileName,image);
                    addShopImage(shop, imageHolder1);
                } catch (Exception e) {
                    throw new ShopException("添加图片失败");
                }
            }
            shop.setLastEditTime(new Date());
            int i = shopDao.updateShop(shop);
            if (i <= 0) {
                throw new ShopException("修改商铺失败");
            }
            Shop newshop = shopDao.queryByShopId(shop.getShopId());
            return new ShopExecution(ShopSateEnum.SUCCESS, newshop);
        }
    }

    @Override
    public ShopExecution getShopList(Shop shop, int pageNum, int pageSize) {
        int rowIndex = PageMath.pageNumtoRowIndex(pageNum, pageSize);
        List<Shop> shops = shopDao.queryShops(shop, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shop);
        if (shops.size()>=0) {
            ShopExecution shopExecution = new ShopExecution(ShopSateEnum.SUCCESS, shops);
            shopExecution.setCount(count);
            return shopExecution;
        }else {
            return new ShopExecution(ShopSateEnum.INNER_ERROP);
        }
    }

    /**
     * 添加图片
     * @param shop 商铺信息（只是利用了shopId）
     * @param imageHolder 图片流
     * @throws RuntimeException 事物管理
     */
    private void addShopImage(Shop shop, ImageHolder imageHolder) throws RuntimeException {
        String shopImgPath = PathUtil.getShopImgPath(shop.getShopId());
        String shopImageAddr = ImageUtil.makingThumbnails(imageHolder, shopImgPath);
        shop.setShopImg(shopImageAddr);
    }
}

