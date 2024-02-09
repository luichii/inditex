package com.inditex.adapter;

import com.inditex.dto.PriceDTO;
import com.inditex.entity.PriceEntity;
import com.inditex.mapper.PriceMapper;
import com.inditex.port.PriceRepositoryPort;
import com.inditex.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;

    private final PriceMapper priceMapper;

    @Override
    public List<PriceDTO> findPricesByBrandIdAndProductIdAndDate(
            Long brandId, Long productId, Timestamp applianceDate) {

        List<PriceEntity> entities = priceRepository.findPricesByBrandIdAndProductIdAndDate(
                brandId, productId, applianceDate);

        return priceMapper.toDto(entities);
    }
}