package com.inditex.port;

import com.inditex.dto.PriceDTO;

import java.time.LocalDateTime;

public interface PriceRepositoryPort {
    PriceDTO findPricesByBrandIdAndProductIdAndDate(
        Long brandId, Long productId, LocalDateTime applianceDate);
}
