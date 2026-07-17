package com.octl2.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.octl2.api.entity.DefaultDelivery;

@Repository
public interface DefaultDeliveryRepository extends JpaRepository<DefaultDelivery, Integer> {

        @Modifying(clearAutomatically = true, flushAutomatically = true)
        @Query(value = "UPDATE cf_default_delivery " +
                        "SET ffm_id = :ffmId, " +
                        "    lastmile_id = :lastmileId, " +
                        "    warehouse_id = :warehouseId " +
                        "WHERE location_id IN (:locationIds) ", nativeQuery = true)
        int updateLogisticsByLocationId(
                        @Param("ffmId") Integer ffmId,
                        @Param("lastmileId") Integer lastmileId,
                        @Param("warehouseId") Integer warehouseId,
                        @Param("locationIds") List<Integer> locationIds);
}
