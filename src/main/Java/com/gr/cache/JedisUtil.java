package com.gr.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;

import java.util.Set;

/**
 * @author maitentai
 * @version 1.0
 * @date 2020-02-21 11:13
 */
public class JedisUtil {
    public Keys KEYS;
    public Strings STRINGS;
    private JedisPool jedisPool;

    public JedisPool getJedisPool() {
        return jedisPool;
    }

    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public class Keys {

        public String flushAll() {
            Jedis jedis = getJedis();
            String stata = jedis.flushAll();
            jedis.close();
            return stata;
        }

        public long del(String... keys) {
            Jedis jedis = getJedis();
            Long del = jedis.del(keys);
            jedis.close();
            return del;
        }

        public Boolean exists(String key) {
            Jedis jedis = getJedis();
            Boolean exists = jedis.exists(key);
            jedis.close();
            return exists;
        }

        public Set<String> keys(String pattern) {
            Jedis jedis = getJedis();
            Set<String> keys = jedis.keys(pattern);
            jedis.close();
            return keys;
        }
    }

    public class Strings {
        public String get(String key) {
            Jedis jedis = getJedis();
            String s = jedis.get(key);
            jedis.close();
            return s;
        }
        public String set(String key, String value){
            byte[] keyByte = SafeEncoder.encode(key);
            byte[] valueButy = SafeEncoder.encode(value);
            Jedis jedis = getJedis();
            String set = jedis.set(keyByte, valueButy);
            jedis.close();
            return set;
        }
    }
}
