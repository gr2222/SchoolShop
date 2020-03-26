package com.gr.web.Interface;

import com.gr.entity.HeadLine;
import com.gr.entity.ShopCategory;
import com.gr.service.HeadLineService;
import com.gr.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-19 11:52
 */
@Controller
@RequestMapping("/reception")
public class IndexController {
    @Autowired
    HeadLineService headLineService;
    @Autowired
    ShopCategoryService shopCategoryService;
    @RequestMapping(value = "/initmainpageinfo", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> initMainPageInfo() {
        Map<String, Object> map = new HashMap<>(3);
        try {
            List<ShopCategory> shopCategoryList = shopCategoryService.getShopCategoryList(null);
            map.put("shopCategoryList", shopCategoryList);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        try {
            HeadLine headLine = new HeadLine();
            headLine.setEnableStatus(1);
            List<HeadLine> headLineList = headLineService.getHeadLineList(headLine);
            map.put("headLineList", headLineList);
        } catch (Exception e) {
            map.put("success", false);
            map.put("errMsg", e.getMessage());
            return map;
        }
        map.put("success", true);
        return map;
    }

}
