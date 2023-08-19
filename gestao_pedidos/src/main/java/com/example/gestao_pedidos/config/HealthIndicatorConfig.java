package com.example.gestao_pedidos.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * Class Health Indicator Configuration
 */
@Slf4j
@Component
public class HealthIndicatorConfig implements HealthIndicator {
    @Override
    public Health health() {
        return new Health.Builder().up().build();
    }
}