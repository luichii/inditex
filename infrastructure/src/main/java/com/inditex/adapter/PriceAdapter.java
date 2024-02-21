package com.inditex.adapter;


import com.inditex.core.model.PriceInfo;
import com.inditex.port.PricePort;
import com.inditex.usecase.GetPriceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PriceAdapter implements PricePort {

    private final GetPriceUseCase getPriceUseCase;

    @Override
    public PriceInfo getPriceInfo(Long brandId, Long productId, LocalDateTime applicationDate) {
        return getPriceUseCase.getPriceInfo(brandId, productId, applicationDate);
    }
}
