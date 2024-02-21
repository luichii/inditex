package com.inditex.usecase.impl;

import com.inditex.dto.PriceDTO;
import com.inditex.mapper.PriceMapper2;
import com.inditex.core.model.PriceInfo;
import com.inditex.port.PriceRepositoryPort;
import com.inditex.usecase.GetPriceUseCase;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    private final PriceMapper2 priceMapper2;

    public GetPriceUseCaseImpl(PriceRepositoryPort priceRepositoryPort, PriceMapper2 priceMapper2) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.priceMapper2 = priceMapper2;
    }

    @Override
    public PriceInfo getPriceInfo(Long brandId, Long productId, LocalDateTime applicationDate) {

        PriceDTO priceDTO = priceRepositoryPort.findPricesByBrandIdAndProductIdAndDate(
            brandId, productId, applicationDate);

        return priceMapper2.toPrice(priceDTO);
    }
}
