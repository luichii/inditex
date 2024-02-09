package com.inditex.port;

import com.inditex.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricePort {
    Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}