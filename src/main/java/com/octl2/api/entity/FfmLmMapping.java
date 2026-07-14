package com.octl2.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cf_mapping_ffm_lm")
public class FfmLmMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long mappingId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "ffm_id")
    private Long ffmId;

    @Column(name = "lastmile_id")
    private Long lastmileId;
}
