package com.octl2.api.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.octl2.api.dto.DistrictLogisticDTO;
import com.octl2.api.dto.PartnerDTO;
import com.octl2.api.dto.WarehouseDTO;
import com.octl2.api.repository.DistrictRepository;
import com.octl2.api.repository.projection.DistrictLogisticProjection;
import com.octl2.api.service.DistrictService;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Value("${logistics.level-mapping:1}")
    private Integer levelMapping;

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<DistrictLogisticDTO> getDistrictLogisticDTOs(Long provinceId) {

        List<DistrictLogisticProjection> rows = districtRepository.findLogisticsLevel1(provinceId);

        if (levelMapping == 1) {
            rows = districtRepository.findLogisticsLevel1(provinceId);
        } else if (levelMapping == 2) {
            rows = districtRepository.findLogisticsLevel2(provinceId);
        } else if (levelMapping == 3) {
            rows = districtRepository.findLogisticsLevel3(provinceId);
        } else {
            throw new IllegalArgumentException("Invalid logistics level mapping: " + levelMapping);
        }

        Map<Long, DistrictLogisticDTO> dMap = new LinkedHashMap<>();

        for (DistrictLogisticProjection row : rows) {
            DistrictLogisticDTO dLogisticDTO = getOrCreateDistrictLogisticDTO(dMap, row);

            addFfmIfAbsent(dLogisticDTO, row);
            addLmIfAbsent(dLogisticDTO, row);
            addWarehouseIfAbsent(dLogisticDTO, row);
        }

        return new ArrayList<>(dMap.values());
    }

    private void addWarehouseIfAbsent(DistrictLogisticDTO dLogisticDTO, DistrictLogisticProjection row) {

        if (row.getWarehouseId() != null) {

            boolean isDuplicate = false;

            for (WarehouseDTO wDto : dLogisticDTO.getWarehouseDTOs()) {
                if (wDto.getId().equals(row.getWarehouseId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                WarehouseDTO wh = new WarehouseDTO();
                wh.setId(row.getWarehouseId());
                wh.setWarehouseName(row.getWarehouseName());
                dLogisticDTO.getWarehouseDTOs().add(wh);
            }
        }
    }

    private void addLmIfAbsent(DistrictLogisticDTO dLogisticDTO, DistrictLogisticProjection row) {
        if (row.getLastmileId() != null) {

            boolean isDuplicate = false;

            for (PartnerDTO lmDto : dLogisticDTO.getLastmiles()) {
                if (lmDto.getId().equals(row.getLastmileId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                PartnerDTO lm = new PartnerDTO();
                lm.setId(row.getLastmileId());
                lm.setName(row.getLastmileName());
                dLogisticDTO.getLastmiles().add(lm);
            }
        }
    }

    private void addFfmIfAbsent(DistrictLogisticDTO dLogisticDTO, DistrictLogisticProjection row) {
        if (row.getFfmId() != null) {

            boolean isDuplicate = false;

            for (PartnerDTO fDto : dLogisticDTO.getFfms()) {
                if (fDto.getId().equals(row.getFfmId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                PartnerDTO ffm = new PartnerDTO();
                ffm.setId(row.getFfmId());
                ffm.setName(row.getFfmName());
                dLogisticDTO.getFfms().add(ffm);
            }
        }
    }

    private DistrictLogisticDTO getOrCreateDistrictLogisticDTO(Map<Long, DistrictLogisticDTO> dMap,
            DistrictLogisticProjection row) {

        DistrictLogisticDTO d = dMap.get(row.getDistrictId());

        if (d == null) {
            d = new DistrictLogisticDTO();
            d.setDistrictId(row.getDistrictId());
            d.setDistrictName(row.getDistrictName());
            dMap.put(row.getDistrictId(), d);
            return d;
        }

        return d;
    }

}
