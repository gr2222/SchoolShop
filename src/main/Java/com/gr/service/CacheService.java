package com.gr.service;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 13:29
 */
public interface CacheService {
    /**
     * 删除某一前缀key的所有缓存
     * @param keyPrefix key
     */
    void removeFromCache(String keyPrefix);
}
