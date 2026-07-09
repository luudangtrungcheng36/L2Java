package com.octl2.api.dto;

public class WarehouseDTO {
    private Long id;
    private String warehouseName;
    private String warehouseShortName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getWarehouseShortName() {
        return warehouseShortName;
    }

    public void setWarehouseShortName(String warehouseShortName) {
        this.warehouseShortName = warehouseShortName;
    }

}
