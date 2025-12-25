package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.service.RouteOptimizationService;
import com.example.demo.service.ShipmentService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {
    
    private final RouteOptimizationResultRepository resultRepository;
    private final ShipmentService shipmentService;

    public RouteOptimizationServiceImpl(RouteOptimizationResultRepository resultRepository, ShipmentService shipmentService) {
        this.resultRepository = resultRepository;
        this.shipmentService = shipmentService;
    }

    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {
        Shipment shipment = shipmentService.getShipment(shipmentId);
        
        // Generate dummy optimization data
        Double optimizedDistance = Math.random() * 100 + 50; // 50-150 km
        Double estimatedFuel = optimizedDistance / shipment.getVehicle().getFuelEfficiency();
        
        RouteOptimizationResult result = new RouteOptimizationResult(
                shipment, optimizedDistance, estimatedFuel, LocalDateTime.now());
        
        return resultRepository.save(result);
    }

    @Override
    public RouteOptimizationResult getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}