package com.octl2.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cf_default_delivery")
@Getter
@Setter
public class DefaultDelivery {

    @Id
    @Column(name = "cf_default_do_id")
    private Integer id;

    @Column(name = "location_id")
    private Integer locationId;

    @Column(name = "ffm_id")
    private Integer ffmId;

    @Column(name = "lastmile_id")
    private Integer lastmileId;

    @Column(name = "warehouse_id")
    private Integer warehouseId;
}
