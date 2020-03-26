package com.gr.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-16 15:01
 */
@Controller
@RequestMapping("/shopAdmin")
public class ProductAdmin {
    @RequestMapping("/productcategorymanage")
    public String productCategoryManage(){
        return "shop/productcategorymanage";
    }
}
