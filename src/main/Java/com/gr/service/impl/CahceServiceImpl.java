package com.gr.service.impl;

import com.gr.cache.JedisUtil;
import com.gr.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 13:31
 */
@Service
public class CahceServiceImpl implements CacheService {
    @Autowired
    private JedisUtil.Keys jedisKeys;

    @Override
    public void removeFromCache(String keyPrefix) {
        Set<String> keys = jedisKeys.keys(keyPrefix + "*");
        for (String s : keys) {
            jedisKeys.del(s);
        }
    }
}
