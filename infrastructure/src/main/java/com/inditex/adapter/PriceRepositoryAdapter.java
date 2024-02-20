package com.inditex.adapter;

import com.inditex.dto.PriceDTO;
import com.inditex.entity.PriceEntity;
import com.inditex.mapper.PriceMapper;
import com.inditex.port.PriceRepositoryPort;
import com.inditex.repository.PriceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Override
    public PriceDTO findPricesByBrandIdAndProductIdAndDate(
        Long brandId, Long productId, LocalDateTime applianceDate) {

        Optional<PriceEntity> priceEntityOptional = priceRepository.findPricesByBrandIdAndProductIdAndDate(
            brandId, productId, applianceDate);

        if (priceEntityOptional.isEmpty()) {
            throw new EntityNotFoundException("Price entity not found");
        }

        return priceMapper.toDto(priceEntityOptional.get());
    }
}
