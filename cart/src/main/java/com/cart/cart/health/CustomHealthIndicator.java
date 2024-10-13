package com.cart.cart.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Custom logic to check health
        boolean isHealthy = checkIfServiceIsHealthy();
        if (isHealthy) {
            return Health.up().withDetail("Server is up", getClass()).build();
        } else {
            return Health.down().withDetail("Error", "Service is down").build();
        }
    }

    private boolean checkIfServiceIsHealthy() {
        // Logic to check service health
        return true;  // Replace with actual check
    }
}

