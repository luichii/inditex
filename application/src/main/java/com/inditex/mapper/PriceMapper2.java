package com.inditex.mapper;

import com.inditex.core.model.PriceInfo;
import com.inditex.dto.PriceDTO;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPrices200ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = BrandMapper.class)
public interface PriceMapper2 {

    @Mapping(target = "brandName", source = "priceInfo.brand.name")
    GetPrices200ResponseDto toDto(PriceInfo priceInfo);

    @Mapping(target = "brand", source = "priceDTO.brandDTO")
    PriceInfo toPrice(PriceDTO priceDTO);
}
