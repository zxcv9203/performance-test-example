package org.example.performancetestexample.config

import org.example.performancetestexample.domain.Post
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.jcache.JCacheCacheManager
import org.springframework.cache.jcache.config.JCacheConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.cache.Caching
import javax.cache.configuration.MutableConfiguration
import javax.cache.spi.CachingProvider

@Configuration
@EnableCaching
class CacheConfig : JCacheConfigurer {

    @Bean
    override fun cacheManager(): CacheManager? {
        val cachingProvider: CachingProvider = Caching.getCachingProvider()
        val cacheManager = cachingProvider.cacheManager

        val configuration = MutableConfiguration<Long, Post>()
            .setTypes(Long::class.javaObjectType, Post::class.java)
            .setStoreByValue(false)
            .setStatisticsEnabled(true)

        cacheManager.createCache("post-cache", configuration)

        return JCacheCacheManager(cacheManager)
    }
}