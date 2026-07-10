package com.octl2.api.service.impl;

import com.octl2.api.dto.PartnerDTO;
import com.octl2.api.dto.ProvinceDTO;
import com.octl2.api.dto.ProvinceLogisticDTO;
import com.octl2.api.dto.WarehouseDTO;
import com.octl2.api.repository.ProvinceRepository;
import com.octl2.api.repository.projection.ProvinceLogisticProjection;
import com.octl2.api.service.ProvinceService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProvinceServiceImpl implements ProvinceService {

    @Value("${logistics.level-mapping:1}")
    private Integer levelMapping;

    private final ProvinceRepository provinceRepo;
    private int orgId = 4;

    @Override
    public ProvinceDTO getBybId(long id) {
        return provinceRepo.getDtoById(id);
    }

    @Override
    public List<ProvinceLogisticDTO> getProvinceLogistic() {

        List<ProvinceLogisticProjection> logisticProjections;

        if (levelMapping == 1) {
            logisticProjections = provinceRepo.findLogisticsLevel1(orgId);
        } else if (levelMapping == 2) {
            logisticProjections = provinceRepo.findLogisticsLevel2(orgId);
        } else if (levelMapping == 3) {
            logisticProjections = provinceRepo.findLogisticsLevel3(orgId);
        } else {
            throw new IllegalArgumentException("Invalid logistics level mapping: " + levelMapping);
        }

        Map<Long, ProvinceLogisticDTO> prMap = new LinkedHashMap<>();

        for (ProvinceLogisticProjection row : logisticProjections) {

            ProvinceLogisticDTO provinceLogisticDTO = getOrCreateProvinceDTO(prMap, row);

            addFfmIfAbsent(provinceLogisticDTO, row);
            addLmIfAbsent(provinceLogisticDTO, row);
            addWarehouseIfAbsent(provinceLogisticDTO, row);

        }

        return new ArrayList<>(prMap.values());
    }

    private void addWarehouseIfAbsent(ProvinceLogisticDTO provinceLogisticDTO, ProvinceLogisticProjection row) {

        if (row.getWarehouseId() != null) {

            boolean isDuplicate = false;

            for (WarehouseDTO pDto : provinceLogisticDTO.getWarehouseDTOs()) {
                if (row.getWarehouseId().equals(pDto.getId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                WarehouseDTO wh = new WarehouseDTO();
                wh.setId(row.getWarehouseId());
                wh.setWarehouseName(row.getWarehouseName());
                provinceLogisticDTO.getWarehouseDTOs().add(wh);
            }
        }
    }

    private void addLmIfAbsent(ProvinceLogisticDTO provinceLogisticDTO, ProvinceLogisticProjection row) {
        if (row.getLastmileId() != null) {

            boolean isDuplicate = false;

            for (PartnerDTO pDto : provinceLogisticDTO.getLastmiles()) {
                if (row.getLastmileId().equals(pDto.getId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                PartnerDTO lm = new PartnerDTO();
                lm.setId(row.getLastmileId());
                lm.setName(row.getLastmileName());
                provinceLogisticDTO.getLastmiles().add(lm);
            }
        }
    }

    private void addFfmIfAbsent(ProvinceLogisticDTO provinceLogisticDTO, ProvinceLogisticProjection row) {

        if (row.getFfmId() != null) {

            boolean isDuplicate = false;

            for (PartnerDTO pDto : provinceLogisticDTO.getFfms()) {
                if (row.getFfmId().equals(pDto.getId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                PartnerDTO ffm = new PartnerDTO();
                ffm.setId(row.getFfmId());
                ffm.setName(row.getFfmName());
                provinceLogisticDTO.getFfms().add(ffm);
            }
        }
    }

    private ProvinceLogisticDTO getOrCreateProvinceDTO(Map<Long, ProvinceLogisticDTO> prMap,
            ProvinceLogisticProjection row) {

        ProvinceLogisticDTO p = prMap.get(row.getProvinceId());

        if (p == null) {
            p = new ProvinceLogisticDTO();

            p.setProvinceId(row.getProvinceId());
            p.setProvinceName(row.getProvinceName());

            prMap.put(row.getProvinceId(), p);
            return p;
        }

        return p;
    }

}
