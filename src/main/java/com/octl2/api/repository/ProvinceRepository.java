package com.octl2.api.repository;

import com.octl2.api.dto.ProvinceDTO;
import com.octl2.api.entity.Province;
import com.octl2.api.repository.projection.ProvinceLogisticProjection;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

        @Query(value = "SELECT " +
                        "   p.province_id AS id " +
                        "   , p.name " +
                        "   , p.shortname " +
                        "   , p.code " +
                        "   , p.dcsr AS description " +
                        " FROM lc_province AS p " +
                        " WHERE " +
                        "   p.province_id = :id ", nativeQuery = true)
        ProvinceDTO getDtoById(@Param("id") long id);

        @Query(value = "SELECT " +
                        "    p.province_id AS provinceId, " +
                        "    p.name AS provinceName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_province p " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = p.province_id " +
                        "      AND cdd.org_id = p.org_id " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE p.org_id = :orgId " +
                        "ORDER BY p.province_id", nativeQuery = true)
        List<ProvinceLogisticProjection> findLogisticsLevel1(
                        @Param("orgId") Integer orgId);

        @Query(value = "SELECT " +
                        "    p.province_id AS provinceId, " +
                        "    p.name AS provinceName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_province p " +
                        "LEFT JOIN lc_district d " +
                        "       ON d.province_id = p.province_id " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = d.district_id " +
                        "      AND cdd.org_id = p.org_id " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE p.org_id = :orgId " +
                        "ORDER BY p.province_id", nativeQuery = true)
        List<ProvinceLogisticProjection> findLogisticsLevel2(
                        @Param("orgId") Integer orgId);

        @Query(value = "SELECT " +
                        "    p.province_id AS provinceId, " +
                        "    p.name AS provinceName, " +
                        "    ffm.partner_id AS ffmId, " +
                        "    ffm.name AS ffmName, " +
                        "    lm.partner_id AS lastmileId, " +
                        "    lm.name AS lastmileName, " +
                        "    wh.warehouse_id AS warehouseId, " +
                        "    wh.warehouse_name AS warehouseName " +
                        "FROM lc_province p " +
                        "LEFT JOIN lc_district d " +
                        "       ON d.province_id = p.province_id " +
                        "LEFT JOIN lc_subdistrict s " +
                        "       ON s.district_id = d.district_id " +
                        "LEFT JOIN cf_default_delivery cdd " +
                        "       ON cdd.location_id = s.subdistrict_id " +
                        "      AND cdd.org_id = p.org_id " +
                        "LEFT JOIN bp_partner ffm " +
                        "       ON ffm.partner_id = cdd.ffm_id " +
                        "      AND ffm.partner_type = 122 " +
                        "LEFT JOIN bp_partner lm " +
                        "       ON lm.partner_id = cdd.lastmile_id " +
                        "      AND lm.partner_type = 121 " +
                        "LEFT JOIN bp_warehouse wh " +
                        "       ON wh.warehouse_id = cdd.warehouse_id " +
                        "WHERE p.org_id = :orgId " +
                        "ORDER BY p.province_id", nativeQuery = true)
        List<ProvinceLogisticProjection> findLogisticsLevel3(
                        @Param("orgId") Integer orgId);

}
