package com.octl2.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.octl2.api.entity.District;
import com.octl2.api.repository.projection.DistrictLogisticProjection;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {

        @Query(value = "SELECT " +
                        "    d.district_id AS districtId, " +
                        "    d.name AS districtName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_district d " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = :provinceId " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE d.province_id = :provinceId " +
                        "ORDER BY d.district_id", nativeQuery = true)
        List<DistrictLogisticProjection> findLogisticsLevel1(@Param("provinceId") Long provinceId);

        @Query(value = "SELECT " +
                        "    d.district_id AS districtId, " +
                        "    d.name AS districtName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_district d " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = d.district_id " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE d.province_id = :provinceId " +
                        "ORDER BY d.district_id", nativeQuery = true)
        List<DistrictLogisticProjection> findLogisticsLevel2(@Param("provinceId") Long provinceId);

        @Query(value = "SELECT " +
                        "    d.district_id AS districtId, " +
                        "    d.name AS districtName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_district d " +
                        "LEFT JOIN lc_subdistrict s " +
                        "       ON s.district_id = d.district_id " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = s.subdistrict_id " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE d.province_id = :provinceId " +
                        "ORDER BY d.district_id", nativeQuery = true)
        List<DistrictLogisticProjection> findLogisticsLevel3(@Param("provinceId") Long provinceId);

        @Query(value = "SELECT " +
                        "d.district_id " +
                        "FROM lc_district d " +
                        "WHERE d.province_id = :provinceId ", nativeQuery = true)
        List<Integer> findByProvinceId(@Param("provinceId") Integer provinceId);
}
