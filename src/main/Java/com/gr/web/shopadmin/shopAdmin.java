package com.gr.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-14 12:05
 */
@Controller
@RequestMapping(value = "/shopAdmin", method = {RequestMethod.GET})
public class shopAdmin {

    @RequestMapping(value = "/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }

    @RequestMapping(value = "/shoplist")
    public String shopList() {
        return "shop/shoplist";
    }

    @RequestMapping(value = "/shopmanage")
    public String shopManage() {
        return "shop/shopmanage";
    }
    @RequestMapping(value = "/productoperation")
    public String productOperation(){
        return "shop/productoperation";
    }

    @RequestMapping(value = "/productlist")
    public String productList(){
        return "shop/productlist";
    }
}
