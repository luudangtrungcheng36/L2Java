package com.octl2.api.service;

import java.util.List;

import com.octl2.api.dto.ProvinceDTO;
import com.octl2.api.dto.ProvinceLogisticDTO;

public interface ProvinceService {

    ProvinceDTO getBybId(long id);

    List<ProvinceLogisticDTO> getProvinceLogistic();
}
