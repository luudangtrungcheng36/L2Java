package com.octl2.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.octl2.api.entity.Subdistrict;
import com.octl2.api.repository.projection.SubdistrictLogisticProjection;

@Repository
public interface SubdistrictRepository extends JpaRepository<Subdistrict, Long> {

        @Query(value = "SELECT " +
                        "    s.subdistrict_id AS subdistrictId, " +
                        "    s.name AS subdistrictName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_subdistrict s " +
                        "LEFT JOIN lc_district d " +
                        "       ON d.district_id = s.district_id " +
                        "LEFT JOIN lc_province p " +
                        "       ON p.province_id = d.province_id " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = p.province_id " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE s.district_id = :districtId " +
                        "ORDER BY s.district_id", nativeQuery = true)
        List<SubdistrictLogisticProjection> findLogisticsLevel1(@Param("districtId") Long districtId);

        @Query(value = "SELECT " +
                        "    s.subdistrict_id AS subdistrictId, " +
                        "    s.name AS subdistrictName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_subdistrict s " +
                        "LEFT JOIN lc_district d " +
                        "       ON d.district_id = s.district_id " +
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
                        "WHERE s.district_id = :districtId " +
                        "ORDER BY s.district_id", nativeQuery = true)
        List<SubdistrictLogisticProjection> findLogisticsLevel2(@Param("districtId") Long districtId);

        @Query(value = "SELECT " +
                        "    s.subdistrict_id AS subdistrictId, " +
                        "    s.name AS subdistrictName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_subdistrict s " +
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
                        "WHERE s.district_id = :districtId " +
                        "ORDER BY s.district_id", nativeQuery = true)
        List<SubdistrictLogisticProjection> findLogisticsLevel3(@Param("districtId") Long districtId);

        @Query(value = "SELECT " +
                        "s.subdistrict_id " +
                        "FROM lc_subdistrict s " +
                        "JOIN lc_district d " +
                        "       ON d.district_id = s.district_id " +
                        "JOIN lc_province p " +
                        "       ON p.province_id = d.province_id " +
                        "WHERE p.province_id = :provinceId ", nativeQuery = true)
        List<Integer> findSubdistrictIdByProvinceId(@Param("provinceId") Integer provinceId);

        @Query(value = "SELECT " +
                        "s.subdistrict_id " +
                        "FROM lc_subdistrict s " +
                        "JOIN lc_district d " +
                        "       ON d.district_id = s.district_id " +
                        "WHERE d.district_id = :districtId ", nativeQuery = true)
        List<Integer> findSubdistrictIdByDistrictId(@Param("districtId") Integer districtId);
}
