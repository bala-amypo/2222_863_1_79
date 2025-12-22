package com.example.demo.controller;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/optimize")
public class RouteOptimizationController {
    
    private final RouteOptimizationService routeOptimizationService;
    
    public RouteOptimizationController(RouteOptimizationService routeOptimizationService) {
        this.routeOptimizationService = routeOptimizationService;
    }
    
    @PostMapping("/optimize/{shipmentId}")
    public ResponseEntity<RouteOptimizationResult> optimizeRoute(@PathVariable Long shipmentId) {
        RouteOptimizationResult result = routeOptimizationService.optimizeRoute(shipmentId);
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/optimize/result/{resultId}")
    public ResponseEntity<RouteOptimizationResult> getResult(@PathVariable Long resultId) {
        RouteOptimizationResult result = routeOptimizationService.getResult(resultId);
        return ResponseEntity.ok(result);
    }
}