package com.wmata.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("trainPredictions", "stationInfo");
    }

    // 每30秒清除一次缓存，强制刷新数据
    @Scheduled(fixedRate = 30000)
    public void clearCache() {
        cacheManager().getCache("trainPredictions").clear();
    }
}