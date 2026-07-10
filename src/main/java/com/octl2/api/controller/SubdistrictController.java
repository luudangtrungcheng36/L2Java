package com.octl2.api.controller;

import java.util.List;

import org.checkerframework.checker.units.qual.s;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octl2.api.dto.DistrictLogisticDTO;
import com.octl2.api.dto.SubdistrictLogisticDTO;
import com.octl2.api.service.SubdistrictService;

@RestController
@RequestMapping("/api/v1/subdistricts")
public class SubdistrictController {

    private final SubdistrictService subdistrictService;

    public SubdistrictController(SubdistrictService subdistrictService) {
        this.subdistrictService = subdistrictService;
    }

    @GetMapping("/logistic/{id}")
    public List<SubdistrictLogisticDTO> getSubdistrictLogistics(@PathVariable Long id) {

        List<SubdistrictLogisticDTO> results = subdistrictService.getDistrictLogisticDTOs(id);

        return results;
    }
}
