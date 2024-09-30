package com.altas.iot.sys.utils;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;

/**
 * @ClassName: TimedCacheUtils
 * @Description: 时间缓存工具类
 * @Author: LiHanzhang
 * @Date: 2024-09-27 15:14
 * @Email: 9255520@gmail.com
 * @Version: 1.0
 **/
public class TimedCacheUtils {
    //1分钟
    public static TimedCache<String, Object> TIMED_CACHE = CacheUtil.newTimedCache(60000);


    public static void run() {
        //启动定时任务，每1秒清理一次过期条目，注释此行首次启动仍会清理过期条目
        TIMED_CACHE.schedulePrune(1000);
    }
    /**
     * 存入键值对，提供消逝时间
     *
     * @param key
     * @param value
     */
    public static void put(String key, Object value) {
        /** 设置消逝时间 */
        TIMED_CACHE.put(key, value);
    }

    /**
     * 每次重新get一次缓存，均会重新刷新消逝时间
     * @param key
     * @return
     */
    public static Object get(String key) {
        return TIMED_CACHE.get(key);
    }
}
