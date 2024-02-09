package com.inditex.port;

import com.inditex.dto.PriceDTO;

import java.sql.Timestamp;
import java.util.List;

public interface PriceRepositoryPort {
    List<PriceDTO> findPricesByBrandIdAndProductIdAndDate(
            Long brandId, Long productId, Timestamp applianceDate);
}
