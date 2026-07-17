package com.octl2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octl2.api.dto.DefaultDeliveryDTO;
import com.octl2.api.service.DefaultDeliveryService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/default_delivery")
public class DefaultDeliveryController {

    private final DefaultDeliveryService defaultDeliveryService;

    public DefaultDeliveryController(DefaultDeliveryService defaultDeliveryService) {
        this.defaultDeliveryService = defaultDeliveryService;
    }

    @PostMapping("/{levelLocation}/{locationId}")
    public String updateLogistics(@PathVariable("levelLocation") Integer levelLocation,
            @PathVariable("locationId") Integer locationId, @RequestBody DefaultDeliveryDTO defaultDeliveryDTO) {

        defaultDeliveryService.updateLogistics(levelLocation, locationId, defaultDeliveryDTO);

        return "Oke rồi";
    }

}
