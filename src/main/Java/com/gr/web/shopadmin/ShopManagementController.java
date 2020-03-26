package com.gr.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.dto.ImageHolder;
import com.gr.dto.ShopExecution;
import com.gr.entity.Area;
import com.gr.entity.PersonInfo;
import com.gr.entity.Shop;
import com.gr.entity.ShopCategory;
import com.gr.enums.ShopSateEnum;
import com.gr.service.AreaService;
import com.gr.service.ShopCategoryService;
import com.gr.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-14 10:05
 */
@Controller
@RequestMapping("/shopAdmin")
public class ShopManagementController {
    @Autowired
    private ShopService shopService;
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> registerShop(@RequestParam("shopStr") String shopStr, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest =
                    (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            map.put("success", false);
            map.put("errMsg", "没有上传图片/图片上传出错");
            return map;
        }
        if (shop != null && shopImg != null) {
            PersonInfo personInfo = (PersonInfo) request.getSession().getAttribute("personInfoSession");
            shop.setPersonInfo(personInfo);
            ShopExecution se = null;
            try {
                ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
                se = shopService.addShop(shop, imageHolder);
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", "注册商铺时出错");
                return map;
            }
            if (se.getState() == ShopSateEnum.REVIEW.getState()) {
                map.put("success", true);
                return map;
            } else {
                map.put("success", false);
                map.put("errMsg", se.getStateInfo());
                return map;
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "信息不完整");
            return map;
        }
    }

    @RequestMapping(value = "/getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopInitInfo() {
        Map<String, Object> map = new HashMap<>(3);
        List<Area> areas;
        List<ShopCategory> shopCategoryList;
        try {
            areas = areaService.getAreas();
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            map.put("success", true);
            map.put("shopCategoryList", shopCategoryList);
            map.put("areaList", areas);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", "读取数据出错:" + e.getMessage());
        }
        return map;
    }

    @RequestMapping(value = "/getshopbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopById(@RequestParam(value = "shopId", required = false) Long shopId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        if (shopId <= -1) {
            map.put("success", false);
            map.put("errMsg", "shopId错误");
            return map;
        }
        try {
            Shop shopofSession = (Shop) request.getSession().getAttribute("shopSession");
            if (!shopofSession.getShopId().equals(shopId)) {
                map.put("success", false);
                map.put("errMsg", "权限错误");
                return map;
            }
            Shop shop = shopService.getByShopId(shopId);
            List<Area> areas = areaService.getAreas();
            map.put("success", true);
            map.put("shop", shop);
            map.put("areaList", areas);
            return map;
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", "获取信息失败");
            return map;
        }
    }

    @RequestMapping(value = "/updateshop", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateShop(@RequestParam("shopStr") String shopStr,
                                          @RequestParam("isfile") Boolean isfile,
                                          HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        if (shopStr == null) {
            map.put("success", false);
            map.put("errMsg", "数据错误");
            return map;
        }
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("success", false);
            map.put("errMsg", "数据错误");
            return map;
        }
        if (shop == null && shop.getShopId() == null) {
            map.put("success", false);
            map.put("errmsg", "店铺信息为空,或者没有标示符");
            return map;
        }
        InputStream imgStream = null;
        String fileName = null;
        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver =
                new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request) && isfile) {
            MultipartHttpServletRequest multipartHttpServletRequest =
                    (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");

            fileName = shopImg.getOriginalFilename();
            try {
                imgStream = shopImg.getInputStream();
            } catch (IOException e) {
                map.put("success", false);
                map.put("errMsg", "图片数据错误");
                return map;
            }
        }
        ImageHolder imageHolder = new ImageHolder(fileName, imgStream);
        ShopExecution shopExecution = shopService.updateShop(shop, imageHolder);
        if (shopExecution.getState() == ShopSateEnum.SUCCESS.getState()) {
            map.put("success", true);
            return map;
        } else {
            map.put("success", false);
            map.put("errMsg", "更新数据错误");
            return map;
        }
    }

    @RequestMapping(value = "/getshoplist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getShopList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        Shop shop = new Shop();
        PersonInfo user = (PersonInfo) request.getSession().getAttribute("personInfoSession");
        shop.setPersonInfo(user);
        ShopExecution shopMsg = shopService.getShopList(shop, 0, 100);
        if (shopMsg.getState() == ShopSateEnum.SUCCESS.getState()) {
            request.getSession().setAttribute("shopListSession", shopMsg.getShopList());
            map.put("success", true);
            map.put("shopList", shopMsg.getShopList());
            map.put("user", user);
            return map;
        } else {
            map.put("success", false);
            map.put("errMsg", "读取失败");
            return map;
        }
    }

    @RequestMapping("/setshopsession")
    @ResponseBody
    public Map<String, Object> setShopSession(@RequestParam(value = "shopId", required = false) Long shopId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        if (shopId==null || shopId <= 0) {
            Shop shopSession = (Shop) request.getSession().getAttribute("shopSession");
            if (shopSession == null) {
                map.put("redirect", true);
                map.put("url", "/SchoolShop/shopAdmin/shoplist");
            } else {
                Shop shop = (Shop) shopSession;
                map.put("redirect", false);
                map.put("shopId", shop.getShopId());
            }
        } else {
            Shop shop = new Shop();
            shop.setShopId(shopId);
            HttpSession session = request.getSession();
            session.setAttribute("shopSession", shop);
            map.put("redirect", false);
        }
        return map;
    }
}
