package com.gr.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.dto.ImageHolder;
import com.gr.dto.ProductCategoryExecution;
import com.gr.dto.ProductExecution;
import com.gr.entity.Product;
import com.gr.entity.ProductCategory;
import com.gr.entity.Shop;
import com.gr.enums.ProductCategoryEnum;
import com.gr.enums.ProductSateEnum;
import com.gr.service.ProductCategoryService;
import com.gr.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 14:56
 */
@Controller
@RequestMapping("/shopAdmin")
public class ProductManagementController {
    @Autowired
    ProductCategoryService productCategoryService;
    @Autowired
    ProductService productService;

    private static final int IMAGEMAXCOUNT = 6;

    @RequestMapping("/getpclsit")
    @ResponseBody
    public Map<String, Object> getProductCategoryList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        Shop shopSession = (Shop) request.getSession().getAttribute("shopSession");
        if (shopSession == null) {
            map.put("success", false);
            map.put("errMsg", "权限不对");
            return map;
        }
        Long shopId = shopSession.getShopId();

        List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(shopId);
        if (productCategoryList == null) {
            map.put("success", false);
            map.put("errMsg", "出错");
        }
        if (productCategoryList.size() > 0) {
            map.put("success", true);
            map.put("productcategorycist", productCategoryList);
        } else {
            map.put("success", false);
            map.put("errMsg", "为空");
        }
        return map;
    }

    @RequestMapping(value = "/addproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProductCategorys(@RequestBody List<ProductCategory> list, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        Shop shop = (Shop) request.getSession().getAttribute("shopSession");
        if (shop == null || shop.getShopId() == null) {
            map.put("success", false);
            map.put("errMsg", "权限验证不正确");
            return map;
        }
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                ProductCategory productCategory = list.get(i);
                productCategory.setCreateTime(new Date());
                productCategory.setShopId(shop.getShopId());
                if (productCategory.getPriority()==null||productCategory.getProductCategoryName()==null){
                    list.remove(i);
                }
            }
            try {
                ProductCategoryExecution pe = productCategoryService.batchAddProductCategory(list);
                if (pe.getState() == ProductCategoryEnum.SUCCESS.getState()) {
                    map.put("success", true);
                } else {
                    map.put("success", false);
                    map.put("errMsg", pe.getStateInfo());
                }
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", e.toString());
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "请至少输入一个");
        }
        return map;
    }

    @RequestMapping(value = "/deleteproductcategorys", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProductCategorys(Long productCategoryId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        if (productCategoryId != null && productCategoryId > 0) {
            try {
                Shop shop = (Shop) request.getSession().getAttribute("shopSession");
                if (shop != null && shop.getShopId() != null) {
                    ProductCategoryExecution productCategoryExecution = productCategoryService.deleteProductCategory(productCategoryId, shop.getShopId());
                    if (productCategoryExecution.getState() == ProductCategoryEnum.SUCCESS.getState()) {
                        map.put("success", true);
                    } else {
                        map.put("success", false);
                        map.put("errMsg", productCategoryExecution.getStateInfo());
                    }
                } else {
                    map.put("success", false);
                    map.put("errMsg", "权限问题");
                }
            } catch (Exception e) {
                map.put("success", false);
                map.put("errMsg", e.toString());
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "没有需要删除的分类id");
        }
        return map;
    }

    @RequestMapping(value = "/addproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addProduct(@RequestParam("productStr") String productStr, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper mapper = new ObjectMapper();
        if (productStr != null) {
            Product product;
            try {
                product = mapper.readValue(productStr, Product.class);
            } catch (IOException e) {
                map.put("success", false);
                map.put("errMsg", "数据错误" + e.getMessage());
                return map;
            }
            ImageHolder imageHolder;
            List<ImageHolder> list = new ArrayList<>();
            CommonsMultipartFile productImg = null;
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                productImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg");
                try {
                    imageHolder = new ImageHolder(productImg.getOriginalFilename(), productImg.getInputStream());
                    for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                        CommonsMultipartFile file = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImgs" + i);
                        if (file != null) {
                            ImageHolder imageHolder1 = new ImageHolder(file.getOriginalFilename(), file.getInputStream());
                            list.add(imageHolder1);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    map.put("success", false);
                    map.put("errMsg", "图片上传错误" + e.getMessage());
                    return map;
                }
            } else {
                map.put("success", false);
                map.put("errMsg", "至少需要填写缩略图");
                return map;

            }
            Shop shop = (Shop) request.getSession().getAttribute("shopSession");
            if (shop != null && shop.getShopId() != null) {
                product.setShop(shop);
                try {
                    ProductExecution productExecution = productService.addProduct(product, imageHolder, list);
                    map.put("success", true);
                    return map;
                } catch (Exception e) {
                    map.put("success", false);
                    map.put("errMsg", e.getMessage());
                    return map;
                }

            } else {
                map.put("success", false);
                map.put("errMsg", "权限不够");
                return map;
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "需要填写信息");
            return map;
        }
    }

    @RequestMapping(value = "/getproductbyid", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductById(@RequestParam("productId") Long productId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        if (productId <= -1) {
            map.put("success", false);
            map.put("errMsg", "信息错误");
            return map;
        }
        Product product = productService.getProductById(productId);
        Shop shopSession = (Shop) request.getSession().getAttribute("shopSession");
        if (shopSession.getShopId().equals(product.getShop().getShopId())) {
            map.put("success", true);
            map.put("product", product);
            return map;
        } else {
            map.put("success", true);
            map.put("errMsg", "权限不对");
            return map;
        }
    }


    @RequestMapping(value = "/updateproduct", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> updateProduct(@RequestParam(value = "productStr",required = false) String productStr,
                                             @RequestParam(value = "isfile",required = false) Boolean isfile,
                                             HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        ObjectMapper mapper = new ObjectMapper();
        if (productStr != null) {
            Product product;
            try {
                product = mapper.readValue(productStr, Product.class);
            } catch (IOException e) {
                map.put("success", false);
                map.put("errMsg", "数据错误" + e.getMessage());
                return map;
            }

            ImageHolder imageHolder = null;
            List<ImageHolder> list = new ArrayList<>();
            CommonsMultipartFile productImg = null;
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (commonsMultipartResolver.isMultipart(request)&&isfile) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                productImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImg");
                try {
                    imageHolder = new ImageHolder(productImg.getOriginalFilename(), productImg.getInputStream());
                    for (int i = 0; i < IMAGEMAXCOUNT; i++) {
                        CommonsMultipartFile file = (CommonsMultipartFile) multipartHttpServletRequest.getFile("productImgs" + i);
                        if (file != null) {
                            ImageHolder imageHolder1 = new ImageHolder(file.getOriginalFilename(), file.getInputStream());
                            list.add(imageHolder1);
                        } else {
                            break;
                        }
                    }
                } catch (Exception e) {
                    map.put("success", false);
                    map.put("errMsg", "图片上传时出现错误" + e.getMessage());
                    return map;
                }
            }
            Shop shop = (Shop) request.getSession().getAttribute("shopSession");
            if (shop != null && shop.getShopId() != null) {
                product.setShop(shop);
                try {
                    ProductExecution productExecution = productService.updataProduct(product, imageHolder, list);
                    map.put("success", true);
                    return map;
                } catch (Exception e) {
                    map.put("success", false);
                    map.put("errMsg", e.getMessage());
                    return map;
                }

            } else {
                map.put("success", false);
                map.put("errMsg", "权限不够");
                return map;
            }
        } else {
            map.put("success", false);
            map.put("errMsg", "需要填写信息");
            return map;
        }
    }

    @RequestMapping(value = "/getproductlist", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getProductList(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(3);
        Product product = new Product();
        Shop shopSession = (Shop) request.getSession().getAttribute("shopSession");
        product.setShop(shopSession);

        ProductExecution productList = productService.getProductList(product, 0, 100);
        if (productList.getState()== ProductSateEnum.SUCCESS.getState()){
            map.put("success",true);
            map.put("productlist",productList.getList());
            return map;
        }else {
            map.put("success",false);
            map.put("errMsg",productList.getStateInfo());
            return map;
        }
    }
}

