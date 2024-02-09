package com.inditex.usecase.impl;

import com.inditex.dto.PriceDTO;
import com.inditex.mapper.PriceMapper2;
import com.inditex.model.Price;
import com.inditex.port.PriceRepositoryPort;
import com.inditex.usecase.GetPriceUseCase;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GetPriceUseCaseImpl implements GetPriceUseCase {

    private final PriceRepositoryPort priceRepositoryPort;

    private final PriceMapper2 priceMapper2;

    public GetPriceUseCaseImpl(PriceRepositoryPort priceRepositoryPort, PriceMapper2 priceMapper2) {
        this.priceRepositoryPort = priceRepositoryPort;
        this.priceMapper2 = priceMapper2;
    }

    @Override
    public Optional<Price> getPrice(Long brandId, Long productId, LocalDateTime applicationDate) {

        Timestamp applicationDateTimestamp = Timestamp.valueOf(applicationDate); // Convert LocalDateTime to Timestamp

        List<PriceDTO> prices = priceRepositoryPort.findPricesByBrandIdAndProductIdAndDate(
                brandId, productId, applicationDateTimestamp);

        if (prices.isEmpty()) {
            return Optional.empty();
        }

        Price price = priceMapper2.toPrice(prices.get(0));

        return Optional.of(price);
    }
}