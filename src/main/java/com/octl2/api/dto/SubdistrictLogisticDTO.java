package com.octl2.api.dto;

import java.util.ArrayList;
import java.util.List;

public class SubdistrictLogisticDTO {
    private Long subdistrictId;
    private String subdistrictName;
    private List<PartnerDTO> ffms = new ArrayList<>();
    private List<PartnerDTO> lastmiles = new ArrayList<>();
    private List<WarehouseDTO> warehouseDTOs = new ArrayList<>();

    public Long getSubdistrictId() {
        return subdistrictId;
    }

    public void setSubdistrictId(Long subdistrictId) {
        this.subdistrictId = subdistrictId;
    }

    public String getSubdistrictName() {
        return subdistrictName;
    }

    public void setSubdistrictName(String subdistrictName) {
        this.subdistrictName = subdistrictName;
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
