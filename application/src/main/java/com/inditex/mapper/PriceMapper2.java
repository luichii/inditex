package com.inditex.mapper;

import com.inditex.dto.PriceDTO;
import com.inditex.model.Price;
import com.inditex.openapi.samples.gen.springbootserver.model.GetPrices200ResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = DateMapper.class)
public interface PriceMapper2 {

    @Mapping(target = "startDate", source = "startDate", qualifiedByName = "mapToLocalDateTime")
    @Mapping(target = "endDate", source = "endDate", qualifiedByName = "mapToLocalDateTime")
    @Mapping(target = "brandName", source= "price.brand.name")
    GetPrices200ResponseDto toDto(Price price);

    Price toPrice(PriceDTO priceDTO);
}
