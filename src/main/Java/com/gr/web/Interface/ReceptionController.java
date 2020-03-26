package com.gr.web.Interface;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-18 19:42
 */
@Controller
@RequestMapping("/reception")
public class ReceptionController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "reception/index";
    }

    @RequestMapping(value = "/shoplist", method = RequestMethod.GET)
    public String shopList() {
        return "reception/shoplist";
    }

    @RequestMapping(value = "/shopdetail", method = RequestMethod.GET)
    public String shopDetail() {
        return "reception/shopdetail";
    }

    @RequestMapping(value = "/productdetail", method = RequestMethod.GET)
    public String productDetail() {
        return "reception/productdetail";
    }
}
