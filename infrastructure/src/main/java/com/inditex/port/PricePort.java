package com.inditex.port;

import com.inditex.core.model.PriceInfo;

import java.time.LocalDateTime;

public interface PricePort {
    PriceInfo getPriceInfo(Long brandId, Long productId, LocalDateTime applicationDate);
}
