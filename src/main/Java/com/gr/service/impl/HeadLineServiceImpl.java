package com.gr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.cache.JedisUtil;
import com.gr.dao.HeadLineDao;
import com.gr.entity.HeadLine;
import com.gr.service.HeadLineService;
import com.sun.xml.internal.ws.handler.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-18 15:53
 */
@Service
public class HeadLineServiceImpl implements HeadLineService {
    @Autowired
    HeadLineDao headLineDao;
    @Autowired
    JedisUtil.Keys jedisKeys;
    @Autowired
    JedisUtil.Strings jedisStrings;
    private static String HEADLINEKEY = "headlinelist";

    @Override
    @Transactional
    public List<HeadLine> getHeadLineList(HeadLine headLine) {
        String key = HEADLINEKEY;
        List<HeadLine> headLineList = null;
        ObjectMapper mapper = new ObjectMapper();
        if (headLine !=null&&headLine.getEnableStatus()!=null){
            key = key+"_"+headLine.getEnableStatus();
        }
        if (!jedisKeys.exists(key)) {
            headLineList = headLineDao.queryHeadLine(headLine);
            String headlinesStr = null;
            try {
                headlinesStr = mapper.writeValueAsString(headLineList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new HandlerException(e.getMessage());
            }
            jedisStrings.set(key, headlinesStr);
        }else {
            String headlinesStr = jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, HeadLine.class);
            try {
                headLineList = mapper.readValue(headlinesStr, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                throw new HandlerException(e.getMessage());
            }
        }
        return headLineList;
    }
}
