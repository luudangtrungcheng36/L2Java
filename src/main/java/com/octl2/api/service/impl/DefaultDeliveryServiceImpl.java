package com.octl2.api.service.impl;

import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.octl2.api.dto.DefaultDeliveryDTO;
import com.octl2.api.repository.DefaultDeliveryRepository;
import com.octl2.api.repository.DistrictRepository;
import com.octl2.api.repository.SubdistrictRepository;
import com.octl2.api.service.DefaultDeliveryService;

@Service
public class DefaultDeliveryServiceImpl implements DefaultDeliveryService {

    @Value("${logistics.level-mapping:1}")
    private Integer levelMapping;

    private final DefaultDeliveryRepository defaultDeliveryRepository;
    private final DistrictRepository districtRepository;
    private final SubdistrictRepository subdistrictRepository;

    public DefaultDeliveryServiceImpl(DefaultDeliveryRepository defaultDeliveryRepository,
            DistrictRepository districtRepository, SubdistrictRepository subdistrictRepository) {
        this.defaultDeliveryRepository = defaultDeliveryRepository;
        this.districtRepository = districtRepository;
        this.subdistrictRepository = subdistrictRepository;
    }

    @Transactional
    @Override
    public String updateLogistics(Integer levelLocation, Integer locationId, DefaultDeliveryDTO dto) {

        /*
         * LevelLocation = 1 : Tỉnh
         * LevelLocation = 2 : Huyện
         * LevelLocation = 3 : Xã
         */

        List<Integer> targetLocationIds = null;

        if (levelLocation == levelMapping) {
            targetLocationIds = Collections.singletonList(locationId);
        }

        if (levelLocation == 1 && levelMapping == 2) {
            targetLocationIds = districtRepository.findByProvinceId(locationId);
        }

        if (levelLocation == 1 && levelMapping == 3) {
            targetLocationIds = subdistrictRepository.findSubdistrictIdByProvinceId(locationId);
        }

        if (levelLocation == 2 && levelMapping == 3) {
            targetLocationIds = subdistrictRepository.findSubdistrictIdByDistrictId(locationId);
        }

        defaultDeliveryRepository.updateLogisticsByLocationId(dto.getFfmId(), dto.getLastmileId(), dto.getWarehouseId(),
                targetLocationIds);
        return "Oke";
    }
}
