package com.octl2.api.service;

import java.util.List;

import com.octl2.api.dto.DistrictLogisticDTO;

public interface DistrictService {

    List<DistrictLogisticDTO> getDistrictLogisticDTOs(Long provinceId);
}
