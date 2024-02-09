package com.inditex.usecase;

import com.inditex.model.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPriceUseCase {
    Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime applicationDate);
}