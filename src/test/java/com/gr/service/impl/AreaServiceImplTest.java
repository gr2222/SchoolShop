package com.gr.service.impl;

import com.gr.entity.Area;
import com.gr.service.AreaService;
import com.gr.service.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import sun.misc.Cache;

import javax.swing.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 12:01
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml","classpath:spring/spring-redis.xml"})
public class AreaServiceImplTest {
    @Autowired
    AreaService areaService;
    @Autowired
    CacheService cacheService;
    @Test
    public void getAreas() {
        List<Area> areas = areaService.getAreas();
        System.out.println(areas.size());
        cacheService.removeFromCache("arealist");
        List<Area> areass = areaService.getAreas();
        System.out.println(areas.size());
    }
}