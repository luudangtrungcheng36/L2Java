package com.octl2.api.service;

import java.util.List;

import com.octl2.api.dto.SubdistrictLogisticDTO;

public interface SubdistrictService {

    List<SubdistrictLogisticDTO> getDistrictLogisticDTOs(Long districtId);
}
