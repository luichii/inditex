package com.inditex.repository;

import com.inditex.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;


@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p " +
            "WHERE p.brand.id = :brandId " +
            "AND p.productId = :productId " +
            "AND p.startDate < :date " +
            "AND p.endDate > :date " +
            "ORDER BY p.priority DESC")
    List<PriceEntity> findPricesByBrandIdAndProductIdAndDate(
            Long brandId, Long productId, Timestamp date);
}
