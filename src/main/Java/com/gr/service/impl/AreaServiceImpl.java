package com.gr.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gr.Exceptions.AreaException;
import com.gr.cache.JedisUtil;
import com.gr.dao.AreaDao;
import com.gr.entity.Area;
import com.gr.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-12 20:37
 */
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private JedisUtil.Keys jedisKeys;
    @Autowired
    private JedisUtil.Strings jedisStrings;

    @Override
    @Transactional
    public List<Area> getAreas() {
        String key = AREALISTKEY;
        List<Area> areas = null;
        ObjectMapper mapper = new ObjectMapper();
        if (!jedisKeys.exists(key)){
            areas = areaDao.queryArea();
            String areaStr = null;
            try {
                areaStr = mapper.writeValueAsString(areas);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new AreaException(e.getMessage());
            }
            String set = jedisStrings.set(key, areaStr);
        }else {
            String areaListStr= jedisStrings.get(key);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Area.class);
            try {
                areas = mapper.readValue(areaListStr, javaType);
            } catch (IOException e) {
                e.printStackTrace();
                throw new AreaException(e.getMessage());
            }
        }
        return areas;
    }
}
