package com.gr.web.Interface;

import com.gr.dto.ProductExecution;
import com.gr.dto.ShopExecution;
import com.gr.entity.*;
import com.gr.enums.ProductSateEnum;
import com.gr.enums.ShopSateEnum;
import com.gr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-19 11:43
 */
@Controller
@RequestMapping("/reception")
public class ReceptionManagermentController {
    @Autowired
    ShopService shopService;
    @Autowired
    ShopCategoryService shopCategoryService;
    @Autowired
    AreaService areaService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductImgService productImgService;

    //每一页大小
    private final static Integer pageSize = 5;

    @RequestMapping(value = "/getshoplistlimit", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopListLimit(@RequestParam(value = "shopCategoryId", required = false) Long shopCategoryId,
                                                @RequestParam(value = "pageNum", required = false) int pageNum,
                                                @RequestParam(value = "areaId", required = false) Integer areaId,
                                                @RequestParam(value = "shopName", required = false) String shopName) {
        Map<String, Object> map = new HashMap<>(3);
        Shop shop = new Shop();

        ShopCategory shopCategory = new ShopCategory();
        shopCategory.setShopCategoryId(shopCategoryId);
        List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
        if (shopCategoryList.size() != 0) {
            ShopCategory shopCategory1 = new ShopCategory();
            shopCategory1.setParent(shopCategory);
            shop.setShopCategory(shopCategory1);
        } else {
            shop.setShopCategory(shopCategory);
        }
        Area area = new Area();
        area.setAreaId(areaId);
        shop.setArea(area);
        shop.setShopName(shopName);
        try {
            ShopExecution shopList = shopService.getShopList(shop, pageNum, pageSize);
            if (shopList.getState() == ShopSateEnum.SUCCESS.getState()) {
                map.put("success", true);
                map.put("shopList", shopList.getShopList());
                map.put("count", shopList.getCount());
                return map;
            } else {
                map.put("success", false);
                map.put("errMsg", "没有商铺");
                return map;
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/getshopcategory", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopCategory(@RequestParam(value = "shopCategoryId", required = false) Long shopCategoryId) {
        Map<String, Object> map = new HashMap<>();
        List<ShopCategory> shopCategoryList;
        try {
            if (shopCategoryId != null) {
                ShopCategory shopCategory = new ShopCategory();
                shopCategory.setShopCategoryId(shopCategoryId);
                shopCategoryList = shopCategoryService.getShopCategoryList(shopCategory);
            } else {
                shopCategoryList = shopCategoryService.getShopCategoryList(null);
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        map.put("shopCategoryList", shopCategoryList);
        return map;
    }

    @RequestMapping(value = "/getarealist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getAreaList() {
        Map<String, Object> map = new HashMap<>(3);
        List<Area> areas = null;
        try {
            areas = areaService.getAreas();
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
        }
        map.put("success", true);
        map.put("areaList", areas);
        return map;
    }

    @RequestMapping(value = "/getproductlist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductList(@RequestParam(value = "productCategoryId", required = false) Long productCategoryId,
                                              @RequestParam(value = "pageNum", required = false) int pageNum,
                                              @RequestParam(value = "shopId") Long shopId,
                                              @RequestParam(value = "productName", required = false) String productName) {
        Map<String, Object> map = new HashMap<>();
        Product product = new Product();
        if (productCategoryId != null) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            product.setProductCategory(productCategory);
        }
        if (productName != null) {
            product.setProductName(productName);
        }
        Shop shop = new Shop();
        shop.setShopId(shopId);
        product.setShop(shop);
        try {
            ProductExecution productList = productService.getProductList(product, pageNum, pageSize);
            if (productList.getState() == ProductSateEnum.SUCCESS.getState()) {
                map.put("success", true);
                map.put("productList", productList.getList());
                map.put("count", productList.getCount());
            } else {
                map.put("success", false);
                map.put("errMsg", "数据查询出错");
            }
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        return map;
    }

    @RequestMapping(value = "/getproductcategorylist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductCategoryList(@RequestParam("shopId") Long shopId) {
        Map<String, Object> map = new HashMap<>(3);
        try {
            List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(shopId);
            map.put("productCategoryList", productCategoryList);
            map.put("success", true);
            return map;
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopByid(@RequestParam("shopId") Long shopId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Shop shop = shopService.getByShopId(shopId);
            map.put("success", true);
            map.put("shop", shop);
            return map;
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/getproductandimg", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductAndImg(@RequestParam("productId") Long productId) {
        Map<String, Object> map = new HashMap<>();
        try {
            Product product = productService.getProductById(productId);
            List<ProductImg> productImgList = productImgService.getProductImgList(productId);
            product.setProductImgList(productImgList);
            map.put("success", true);
            map.put("product", product);
            return map;
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
    }

    @RequestMapping(value = "/getloginstate",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getLoginState(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("personInfoSession");
        if (personInfo != null && personInfo.getUserId() != null) {
            map.put("loginState", true);
            map.put("loginStateInfo","成功登录");
            map.put("userType", personInfo.getUserType());
            map.put("userName", personInfo.getName());
            map.put("userImg", personInfo.getProfileImg());
            map.put("integral", personInfo.getIntegral());
        }else {
            map.put("loginState", false);
            map.put("loginStateInfo","没有登录");
        }
        return map;
    }
}
