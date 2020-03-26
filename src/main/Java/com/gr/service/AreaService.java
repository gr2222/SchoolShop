package com.gr.service;

import com.gr.entity.Area;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-12 20:35
 */
public interface AreaService {
    String AREALISTKEY = "arealist";
    /**
     *  得到全部地区信息
     * @return  地区信息列表
     */
    List<Area> getAreas();
}
