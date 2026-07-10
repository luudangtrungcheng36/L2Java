package com.octl2.api.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.octl2.api.dto.PartnerDTO;
import com.octl2.api.dto.SubdistrictLogisticDTO;
import com.octl2.api.dto.WarehouseDTO;
import com.octl2.api.repository.SubdistrictRepository;
import com.octl2.api.repository.projection.SubdistrictLogisticProjection;
import com.octl2.api.service.SubdistrictService;

@Service
public class SubdistrictServiceImpl implements SubdistrictService {

    @Value("${logistics.level-mapping:1}")
    private Integer levelMapping;

    private final SubdistrictRepository subdistrictRepository;

    public SubdistrictServiceImpl(SubdistrictRepository subdistrictRepository) {
        this.subdistrictRepository = subdistrictRepository;
    }

    @Override
    public List<SubdistrictLogisticDTO> getDistrictLogisticDTOs(Long districtId) {

        List<SubdistrictLogisticProjection> rows;

        if (levelMapping == 1) {
            rows = subdistrictRepository.findLogisticsLevel1(districtId);
        } else if (levelMapping == 2) {
            rows = subdistrictRepository.findLogisticsLevel2(districtId);
        } else if (levelMapping == 3) {
            rows = subdistrictRepository.findLogisticsLevel3(districtId);
        } else {
            throw new IllegalArgumentException(
                    "Invalid logistics level mapping: " + levelMapping);
        }

        Map<Long, SubdistrictLogisticDTO> sMap = new LinkedHashMap<>();

        for (SubdistrictLogisticProjection row : rows) {

            SubdistrictLogisticDTO subdistrictLogisticDTO = getOrCreateSubdistrictLogisticDTO(sMap, row);

            addFfmIfAbsent(subdistrictLogisticDTO, row);
            addLmIfAbsent(subdistrictLogisticDTO, row);
            addWarehouseIfAbsent(subdistrictLogisticDTO, row);
        }

        return new ArrayList<>(sMap.values());
    }

    private void addWarehouseIfAbsent(
            SubdistrictLogisticDTO subdistrictLogisticDTO,
            SubdistrictLogisticProjection row) {

        if (row.getWarehouseId() != null) {

            boolean isDuplicate = false;

            for (WarehouseDTO warehouseDTO : subdistrictLogisticDTO.getWarehouseDTOs()) {

                if (warehouseDTO.getId().equals(row.getWarehouseId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                WarehouseDTO warehouse = new WarehouseDTO();

                warehouse.setId(row.getWarehouseId());
                warehouse.setWarehouseName(row.getWarehouseName());

                subdistrictLogisticDTO
                        .getWarehouseDTOs()
                        .add(warehouse);
            }
        }
    }

    private void addLmIfAbsent(
            SubdistrictLogisticDTO subdistrictLogisticDTO,
            SubdistrictLogisticProjection row) {

        if (row.getLastmileId() != null) {

            boolean isDuplicate = false;

            for (PartnerDTO lastmileDTO : subdistrictLogisticDTO.getLastmiles()) {

                if (lastmileDTO.getId().equals(row.getLastmileId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                PartnerDTO lastmile = new PartnerDTO();

                lastmile.setId(row.getLastmileId());
                lastmile.setName(row.getLastmileName());

                subdistrictLogisticDTO
                        .getLastmiles()
                        .add(lastmile);
            }
        }
    }

    private void addFfmIfAbsent(
            SubdistrictLogisticDTO subdistrictLogisticDTO,
            SubdistrictLogisticProjection row) {

        if (row.getFfmId() != null) {

            boolean isDuplicate = false;

            for (PartnerDTO ffmDTO : subdistrictLogisticDTO.getFfms()) {

                if (ffmDTO.getId().equals(row.getFfmId())) {
                    isDuplicate = true;
                    break;
                }
            }

            if (!isDuplicate) {
                PartnerDTO ffm = new PartnerDTO();

                ffm.setId(row.getFfmId());
                ffm.setName(row.getFfmName());

                subdistrictLogisticDTO
                        .getFfms()
                        .add(ffm);
            }
        }
    }

    private SubdistrictLogisticDTO getOrCreateSubdistrictLogisticDTO(
            Map<Long, SubdistrictLogisticDTO> sMap,
            SubdistrictLogisticProjection row) {

        SubdistrictLogisticDTO subdistrict = sMap.get(row.getSubdistrictId());

        if (subdistrict == null) {
            subdistrict = new SubdistrictLogisticDTO();

            subdistrict.setSubdistrictId(row.getSubdistrictId());
            subdistrict.setSubdistrictName(row.getSubdistrictName());

            sMap.put(row.getSubdistrictId(), subdistrict);
        }

        return subdistrict;
    }
}