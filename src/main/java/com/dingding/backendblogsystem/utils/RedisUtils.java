package com.dingding.backendblogsystem.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 设置 key-value，并设置过期时间（单位：秒）
     */
    public void set(String key, Object value, long timeout) {
        redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 根据 key 获取 value
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据 key 删除数据
     */
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 判断 key 是否存在
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置 Hash 类型的数据，并设置过期时间（单位：秒）
     */
    public void hashSet(String key, Map<String, Object> map, long timeout) {
        redisTemplate.opsForHash().putAll(key, map);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取 Hash 类型的数据
     */
    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    /**
     * 在 List 类型的数据尾部添加元素，并设置过期时间（单位：秒）
     */
    public void listPush(String key, Object value, long timeout) {
        redisTemplate.opsForList().rightPush(key, value);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取 List 类型的数据（从 start 到 end）
     */
    public List<Object> listGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 添加 Set 类型的数据，并设置过期时间（单位：秒）
     */
    public void setAdd(String key, Object[] values, long timeout) {
        redisTemplate.opsForSet().add(key, values);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 删除 Set 类型的数据
     */
    public Long setRemove(String key, Object[] values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 添加 ZSet 类型的数据，并设置过期时间（单位：秒）
     */
    public void zSetAdd(String key, Object value, double score, long timeout) {
        redisTemplate.opsForZSet().add(key, value, score);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取 ZSet 类型的数据（从 start 到 end）
     */
    public List<Object> zSetRange(String key, long start, long end) {
        return (List<Object>) redisTemplate.opsForZSet().range(key, start, end);
    }

    /**
     * 获取所有的 key
     */
    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    /**
     * 删除所有的 key
     * <p>
     * /**
     * 删除所有的 key
     */
    public void deleteAllKeys() {
        Set<String> keys = getAllKeys();
        redisTemplate.delete(keys);
    }

    /**
     * 获取 Redis 中所有 key-value，并将其封装为 Map 对象
     *
     * @return Redis 中所有 key-value 的 Map 对象
     */
    public Map<String, Object> getAllKeyValues() {
        Map<String, Object> map = new HashMap<>();
        Set<String> keys = getAllKeys();
        if (!CollectionUtils.isEmpty(keys)) {
            List<Object> values = redisTemplate.opsForValue().multiGet(keys);
            for (int i = 0; i < keys.size(); i++) {
                map.put(keys.toArray(new String[0])[i], values.get(i));
            }
        }
        return map;
    }

}
