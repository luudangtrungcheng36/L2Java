package com.octl2.api.service;

import com.octl2.api.dto.DefaultDeliveryDTO;

public interface DefaultDeliveryService {
    String updateLogistics(Integer levelLocation, Integer locationId, DefaultDeliveryDTO dto);
}
