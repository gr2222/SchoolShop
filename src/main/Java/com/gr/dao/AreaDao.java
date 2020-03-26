package com.gr.dao;

import com.gr.entity.Area;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-12 15:25
 */
public interface AreaDao {
    /**
     * 查询处区域列表
     * @return 区域列表
     */
    List<Area> queryArea();

}
