package com.bootcamp.product_service.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

/**
 * Configuración base de Resilience4j para tolerancia a fallos.
 */
@Configuration
public class Resilience4jConfig {

    @Bean
    public io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .slidingWindowSize(5)
                .build();
        return io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry.of(config);
    }

    @Bean
    public io.github.resilience4j.timelimiter.TimeLimiterRegistry timeLimiterRegistry() {
        TimeLimiterConfig config = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofSeconds(3))
                .build();
        return io.github.resilience4j.timelimiter.TimeLimiterRegistry.of(config);
    }
}
