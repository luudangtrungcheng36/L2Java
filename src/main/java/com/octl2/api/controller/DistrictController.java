package com.octl2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octl2.api.dto.DistrictLogisticDTO;
import com.octl2.api.service.DistrictService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/districts")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/logistic/{id}")
    public List<DistrictLogisticDTO> getDistrictLogistics(@PathVariable Long id) {

        System.out.println("API district logistic called, id = " + id);

        List<DistrictLogisticDTO> results = districtService.getDistrictLogisticDTOs(id);

        System.out.println("District logistic result size = " + results.size());

        return results;
    }

}
