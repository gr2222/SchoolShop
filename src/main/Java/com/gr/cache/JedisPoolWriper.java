package com.gr.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 11:05
 */
public class JedisPoolWriper {
    private JedisPool jedisPool;

    public JedisPoolWriper(final JedisPoolConfig PoolConfig,
                           final String host, final int post){
        try{
            jedisPool = new JedisPool(PoolConfig, host, post);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public JedisPool getJedisPool(){
        return jedisPool;
    }

    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
