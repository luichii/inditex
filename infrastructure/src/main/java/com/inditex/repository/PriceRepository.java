package com.inditex.repository;

import com.inditex.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    @Query("SELECT p FROM PriceEntity p " +
        "JOIN FETCH p.brandEntity b " +
        "WHERE b.id = :brandId " +
        "AND p.productId = :productId " +
        "AND p.startDate < :date " +
        "AND p.endDate > :date " +
        "ORDER BY p.priority DESC")
    Optional<PriceEntity> findPricesByBrandIdAndProductIdAndDate(
        Long brandId, Long productId, LocalDateTime date);
}
