package com.octl2.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultDeliveryDTO {
    private Integer id;
    private Integer locationId;
    private Integer ffmId;
    private Integer lastmileId;
    private Integer warehouseId;
}
