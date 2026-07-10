package com.octl2.api.dto;

import java.util.ArrayList;
import java.util.List;

public class DistrictLogisticDTO {

    private Long districtId;
    private String districtName;
    private List<PartnerDTO> ffms = new ArrayList<>();
    private List<PartnerDTO> lastmiles = new ArrayList<>();
    private List<WarehouseDTO> warehouseDTOs = new ArrayList<>();

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public List<PartnerDTO> getFfms() {
        return ffms;
    }

    public void setFfms(List<PartnerDTO> ffms) {
        this.ffms = ffms;
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
