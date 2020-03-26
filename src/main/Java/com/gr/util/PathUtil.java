package com.gr.util;

import com.gr.entity.Product;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-13 15:15
 */
public class PathUtil {
    private final static String OS = "win";
    private static String separator = System.getProperty("file.separator");

    /**
     * 根据不通操作系统放在不同路径下
     * @return 路径
     */
    public static String getImgBasePath(){
        String property = System.getProperty("os.name");
        String basePath = "";
        if (property.toLowerCase().startsWith(OS)){
            basePath = "D/schoolShop/image";
        }else {
            basePath = "/Users/gaorui/Downloads/image";
            //  /usr/local/image
            //  /Users/gaorui/Downloads/image
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /**
     * 根据商铺id 给每个商铺创建属于自己的图片文件夹路径
     * @param shopId 商铺id
     * @return 图片文件夹路径
     */
    public static String getShopImgPath(long shopId){
        String shopImagePath="/item/shop"+shopId+"/";
        shopImagePath = shopImagePath.replace("/", separator);
        return shopImagePath;
    }

    /**
     * 根据商品id 在 商铺文件夹下创建属于该商品的图片路径
     * @param product
     * @return
     */
    @Deprecated
    public static String getProductPath(Product product){
        String shopImgPath = getShopImgPath(product.getShop().getShopId());
        shopImgPath =shopImgPath+"product"+product.getProductId()+"/";
        shopImgPath = shopImgPath.replace("/", separator);
        return shopImgPath;
    }

}
