package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RouteOptimizationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Shipment shipment;

    private Double optimizedDistanceKm;
    private Double estimatedFuelUsageL;

    private LocalDateTime generatedAt;

    @PrePersist
    public void setGeneratedAt() {
        this.generatedAt = LocalDateTime.now();
    }

    // getters and setters
}
