package com.kb.application.config;

import com.google.common.cache.CacheBuilder;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
@ComponentScan(basePackages = {"com.kb.application"})
public class Config {

    public static final String GEOLOCATION_CACHE = "geolocation";
    public static final String WEATHER_CACHE = "weather";

    @Bean(GEOLOCATION_CACHE)
    public Cache geolocationCache() {
        return new GuavaCache(GEOLOCATION_CACHE, CacheBuilder.newBuilder()
                .expireAfterWrite(1, TimeUnit.DAYS)
                .build());
    }

    @Bean(WEATHER_CACHE)
    public Cache weatherCache() {
        return new GuavaCache(WEATHER_CACHE, CacheBuilder.newBuilder()
                .expireAfterWrite(5, TimeUnit.HOURS)
                .build());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
