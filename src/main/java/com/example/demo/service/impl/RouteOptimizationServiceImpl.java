package com.example.demo.service.impl;

import com.example.demo.entity.RouteOptimizationResult;
import com.example.demo.entity.Shipment;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.RouteOptimizationResultRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.RouteOptimizationService;
import org.springframework.stereotype.Service;

@Service
public class RouteOptimizationServiceImpl implements RouteOptimizationService {
    
    private final RouteOptimizationResultRepository resultRepository;
    private final ShipmentRepository shipmentRepository;
    
    public RouteOptimizationServiceImpl(RouteOptimizationResultRepository resultRepository, ShipmentRepository shipmentRepository) {
        this.resultRepository = resultRepository;
        this.shipmentRepository = shipmentRepository;
    }
    
    @Override
    public RouteOptimizationResult optimizeRoute(Long shipmentId) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Shipment not found"));
        
        // Dummy calculations
        Double distance = 100.0 + (shipmentId % 50);
        Double fuelUsage = distance / shipment.getVehicle().getFuelEfficiency();
        
        RouteOptimizationResult result = new RouteOptimizationResult(shipment, distance, fuelUsage);
        return resultRepository.save(result);
    }
    
    @Override
    public RouteOptimizationResult getResult(Long resultId) {
        return resultRepository.findById(resultId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}