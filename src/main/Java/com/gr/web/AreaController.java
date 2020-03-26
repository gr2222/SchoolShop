package com.gr.web;

import com.gr.entity.Area;
import com.gr.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @date 2020-02-12 20:38
 */
@Controller
@RequestMapping("/superAdmin")
public class AreaController {
    Logger logger = LoggerFactory.getLogger(AreaController.class);
    @Autowired
    private AreaService areaService;
    @RequestMapping(value = "/listArea",method = {RequestMethod.GET})
    @ResponseBody
    public Map<String,Object> listArea(){
        Map<String,Object> respMap = new HashMap<>(4);
        try {
            List<Area> areas = areaService.getAreas();
            respMap.put("success", "true");
            respMap.put("rows", areas);
            respMap.put("total",areas.size());
        }catch (Exception e){
            e.printStackTrace();
            respMap.put("success", "false");
            respMap.put("errMsg", e.toString());
        }
        return respMap;
    }
}
