package com.example.demo.controller;

import com.example.demo.entity.Shipment;
import com.example.demo.service.ShipmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    @PostMapping("/{vehicleId}")
    public Shipment createShipment(@PathVariable Long vehicleId,
                                   @RequestBody Shipment shipment) {
        return shipmentService.createShipment(vehicleId, shipment);
    }

    @GetMapping("/{shipmentId}")
    public Shipment getShipment(@PathVariable Long shipmentId) {
        return shipmentService.getShipment(shipmentId);
    }
}
