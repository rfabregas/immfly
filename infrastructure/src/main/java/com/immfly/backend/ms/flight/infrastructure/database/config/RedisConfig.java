package com.immfly.backend.ms.flight.infrastructure.database.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisConfig {

  @Bean
  RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    return template;
  }

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    return new JedisConnectionFactory();
  }

  @Bean
  public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
    Map<String, RedisCacheConfiguration> cacheConfigurations = new HashMap<>();

    return RedisCacheManager
        .builder(redisConnectionFactory)
        .cacheDefaults(createCacheConfiguration())
        .withInitialCacheConfigurations(cacheConfigurations).build();
  }

  private static RedisCacheConfiguration createCacheConfiguration() {
    return RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofSeconds(30));
  }


}
