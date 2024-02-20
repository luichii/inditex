package com.inditex.usecase;

import com.inditex.model.PriceInfo;

import java.time.LocalDateTime;

public interface GetPriceUseCase {
    PriceInfo getPriceInfo(Long brandId, Long productId, LocalDateTime applicationDate);
}