package com.gr.dao;

import com.gr.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 10:35
 */
public interface ShopDao {
    /**
     * 添加店铺
     * @param shop 商铺
     * @return 影响 行数
     */
    int insertShop(Shop shop);

    /**
     * 更新店铺
     * @param shop 商铺
     * @return 影响行数
     */
    int updateShop(Shop shop);

    /**
     * 根据商铺ID查询商铺信息
     * @param shopId 商铺的id
     * @return 商铺的信息
     */
    Shop queryByShopId(Long shopId);

    /**
     * 分页查询店铺列表，根据店铺名、店铺状态、店铺类别、区域 、拥有者
     * @param shopmsg 店铺的信息
     * @param rowIndex 第几页
     * @param pageSize 每一页多少条
     * @return 店铺列表
     */
    List<Shop> queryShops(@Param("shopmesg")Shop shopmsg,@Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);

    /**
     * 返回queryShops总数
     * @return 数量
     */
    int queryShopCount(@Param("shopmesg")Shop shopmsg);
}
