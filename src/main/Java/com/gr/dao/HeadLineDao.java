package com.gr.dao;

import com.gr.entity.HeadLine;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-18 15:00
 */
public interface HeadLineDao {

    /**
     * 根据传入的条件 查询 头条
     * @param headLine 查询条件
     * @return 头条列表
     */
    List<HeadLine> queryHeadLine(@Param("headlinemsg")HeadLine headLine);



}
