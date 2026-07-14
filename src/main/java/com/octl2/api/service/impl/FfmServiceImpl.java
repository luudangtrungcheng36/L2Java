package com.octl2.api.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.octl2.api.dto.FfmLogisticDTO;
import com.octl2.api.dto.PartnerDTO;
import com.octl2.api.dto.WarehouseDTO;
import com.octl2.api.repository.PartnerRepository;
import com.octl2.api.repository.projection.FfmLogisticProjection;
import com.octl2.api.service.FfmService;

@Service
public class FfmServiceImpl implements FfmService {

    private final PartnerRepository partnerRepository;

    public FfmServiceImpl(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    @Override
    public List<FfmLogisticDTO> getFfmLogisticDTOs() {

        List<FfmLogisticProjection> rows = partnerRepository.findFfmLogistics();

        Map<Long, FfmLogisticDTO> ffmMap = new LinkedHashMap<>();

        for (FfmLogisticProjection row : rows) {

            FfmLogisticDTO ffmLogisticDTO = getOrCreateFfmLogisticDTO(ffmMap, row);

            addLmIfAbsent(ffmLogisticDTO, row);
            addWarehouseIfAbsent(ffmLogisticDTO, row);
        }

        return new ArrayList<>(ffmMap.values());
    }

    private FfmLogisticDTO getOrCreateFfmLogisticDTO(
            Map<Long, FfmLogisticDTO> ffmMap,
            FfmLogisticProjection row) {

        FfmLogisticDTO ffmDTO = ffmMap.get(row.getFfmId());

        if (ffmDTO == null) {
            ffmDTO = new FfmLogisticDTO();

            ffmDTO.setFfmId(row.getFfmId());
            ffmDTO.setFfmName(row.getFfmName());

            ffmMap.put(row.getFfmId(), ffmDTO);
        }

        return ffmDTO;
    }

    private void addLmIfAbsent(
            FfmLogisticDTO ffmLogisticDTO,
            FfmLogisticProjection row) {

        if (row.getLastmileId() == null) {
            return;
        }

        boolean isDuplicate = false;

        for (PartnerDTO lmDTO : ffmLogisticDTO.getLastmiles()) {
            if (lmDTO.getId().equals(row.getLastmileId())) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            PartnerDTO lm = new PartnerDTO();

            lm.setId(row.getLastmileId());
            lm.setName(row.getLastmileName());

            ffmLogisticDTO.getLastmiles().add(lm);
        }
    }

    private void addWarehouseIfAbsent(
            FfmLogisticDTO ffmLogisticDTO,
            FfmLogisticProjection row) {

        if (row.getWarehouseId() == null) {
            return;
        }

        boolean isDuplicate = false;

        for (WarehouseDTO warehouseDTO : ffmLogisticDTO.getWarehouseDTOs()) {

            if (warehouseDTO.getId().equals(row.getWarehouseId())) {
                isDuplicate = true;
                break;
            }
        }

        if (!isDuplicate) {
            WarehouseDTO warehouse = new WarehouseDTO();

            warehouse.setId(row.getWarehouseId());
            warehouse.setWarehouseName(row.getWarehouseName());

            ffmLogisticDTO.getWarehouseDTOs().add(warehouse);
        }
    }
}