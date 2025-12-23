package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optimize")
public class RouteOptimizationController {

    private final RouteOptimizationService optimizationService;

    public RouteOptimizationController(RouteOptimizationService optimizationService) {
        this.optimizationService = optimizationService;
    }

    @PostMapping("/{shipmentId}")
    public RouteOptimizationResult optimize(@PathVariable Long shipmentId) {
        return optimizationService.optimizeRoute(shipmentId);
    }
}
