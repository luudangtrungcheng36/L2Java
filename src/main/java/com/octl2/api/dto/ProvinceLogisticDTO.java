package com.octl2.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ProvinceLogisticDTO {
    private Long provinceId;
    private String provinceName;
    private List<PartnerDTO> ffms = new ArrayList<>();
    private List<PartnerDTO> lastmiles = new ArrayList<>();
    private List<WarehouseDTO> warehouseDTOs = new ArrayList<>();

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
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
