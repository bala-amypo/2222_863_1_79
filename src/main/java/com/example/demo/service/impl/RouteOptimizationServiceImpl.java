package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {
    
    private final ShipmentRepository shipmentRepository;
    private final RouteOptimizationResultRepository routeOptimizationResultRepository;
    
    public RouteOptimizationServiceImpl(ShipmentRepository shipmentRepository, RouteOptimizationResultRepository routeOptimizationResultRepository) {
        this.shipmentRepository = shipmentRepository;
        this.routeOptimizationResultRepository = routeOptimizationResultRepository;
    }
    
    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
        
        double latDiff = shipment.getDropLocation().getLatitude() - shipment.getPickupLocation().getLatitude();
        double lonDiff = shipment.getDropLocation().getLongitude() - shipment.getPickupLocation().getLongitude();
        double distance = Math.hypot(latDiff, lonDiff);
        
        double fuelUsage = distance / shipment.getVehicle().getFuelEfficiency();
        
        RouteOptimizationResult result = new RouteOptimizationResult(
                shipment, distance, fuelUsage, LocalDateTime.now()
        );
        
        return routeOptimizationResultRepository.save(result);
    }
    
    @Override
    public RouteOptimizationResult getResult(Long resultId) {
        return routeOptimizationResultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}