package com.inditex.adapter;


import com.inditex.model.Price;
import com.inditex.port.PricePort;
import com.inditex.usecase.GetPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceAdapter implements PricePort {

    private final GetPriceUseCase getPriceUseCase;

    @Override
    public Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
        return getPriceUseCase.getPrice(brandId, productId, applicationDate);
    }
}
