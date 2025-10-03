package com.bootcamp.customer_service.infrastructure.cache;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Component
public class CustomerReactiveCache {

    private final ReactiveRedisTemplate<String, Object> redisTemplate;

    public CustomerReactiveCache(ReactiveRedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Mono<Object> get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Mono<Boolean> put(String key, Object value, Duration ttl) {
        return redisTemplate.opsForValue().set(key, value, ttl);
    }

    public Mono<Boolean> evict(String key) {
        return redisTemplate.opsForValue().delete(key);
    }
}
