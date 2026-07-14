package com.octl2.api.dto;

import java.util.ArrayList;
import java.util.List;

public class FfmLogisticDTO {

    private Long ffmId;
    private String ffmName;
    private List<PartnerDTO> lastmiles = new ArrayList<>();
    private List<WarehouseDTO> warehouseDTOs = new ArrayList<>();

    public Long getFfmId() {
        return ffmId;
    }

    public void setFfmId(Long ffmId) {
        this.ffmId = ffmId;
    }

    public String getFfmName() {
        return ffmName;
    }

    public void setFfmName(String ffmName) {
        this.ffmName = ffmName;
    }

    public List<PartnerDTO> getLastmiles() {
        return lastmiles;
    }

    public void setLastmiles(List<PartnerDTO> lastmiles) {
        this.lastmiles = lastmiles;
    }

    public List<WarehouseDTO> getWarehouseDTOs() {
        return warehouseDTOs;
    }

    public void setWarehouseDTOs(List<WarehouseDTO> warehouseDTOs) {
        this.warehouseDTOs = warehouseDTOs;
    }

}
