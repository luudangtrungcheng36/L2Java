package com.octl2.api.repository.projection;

public interface DistrictLogisticProjection {
    Long getDistrictId();

    String getDistrictName();

    Long getFfmId();

    String getFfmName();

    Long getLastmileId();

    String getLastmileName();

    Long getWarehouseId();

    String getWarehouseName();
}
