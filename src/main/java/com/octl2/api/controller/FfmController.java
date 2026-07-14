package com.octl2.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octl2.api.dto.FfmLogisticDTO;
import com.octl2.api.service.FfmService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/v1/ffm")
public class FfmController {

    private final FfmService ffmService;

    public FfmController(FfmService ffmService) {
        this.ffmService = ffmService;
    }

    @GetMapping()
    public List<FfmLogisticDTO> getFfmLogistics() {

        List<FfmLogisticDTO> results = ffmService.getFfmLogisticDTOs();
        return results;
    }

}
