package com.octl2.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.octl2.api.entity.Partner;
import com.octl2.api.repository.projection.FfmLogisticProjection;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    @Query(value = "SELECT " +
            "    ffm.partner_id AS ffmId, " +
            "    ffm.name AS ffmName, " +
            "    lm.partner_id AS lastmileId, " +
            "    lm.name AS lastmileName, " +
            "    wh.warehouse_id AS warehouseId, " +
            "    wh.warehouse_name AS warehouseName " +
            "FROM  bp_partner ffm " +
            "LEFT JOIN cf_mapping_ffm_lm cmfl " +
            "       ON cmfl.ffm_id = ffm.partner_id " +
            "LEFT JOIN bp_partner lm " +
            "       ON lm.partner_id = cmfl.lastmile_id " +
            "      AND lm.partner_type = 121 " +
            "LEFT JOIN bp_warehouse wh " +
            "       ON wh.ffm_id = ffm.partner_id " +
            "WHERE ffm.partner_type = 122 ", nativeQuery = true)
    List<FfmLogisticProjection> findFfmLogistics();

}
