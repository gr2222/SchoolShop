package com.gr.service;

import com.gr.entity.HeadLine;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-18 15:52
 */
public interface HeadLineService {
    String HEADLINEKEY = "headlinelist";
    /**
     * 根据信息返回HeadLine列表
     * @param headLine 信息
     * @return 返回
     */
   List<HeadLine> getHeadLineList(HeadLine headLine);
}
