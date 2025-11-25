package com.example.productservice.configs;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.springframework.cache.CacheManager;
import org.springframework.cache.jcache.JCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.annotation.EnableCaching;

import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        // Get Ehcache JSR-107 (JCache) provider
        CachingProvider cachingProvider = Caching.getCachingProvider(EhcacheCachingProvider.class.getName());
        javax.cache.CacheManager jsr107CacheManager = cachingProvider.getCacheManager();

        // Define a cache programmatically
        MutableConfiguration<Long, String> config = new MutableConfiguration<>();
        jsr107CacheManager.createCache("productCache", config);

        return new JCacheCacheManager(jsr107CacheManager);
    }
}
