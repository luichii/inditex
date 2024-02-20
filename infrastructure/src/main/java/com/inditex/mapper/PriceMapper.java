package com.inditex.mapper;

import com.inditex.dto.PriceDTO;
import com.inditex.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = BrandMapper2.class)
public interface PriceMapper {

    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "brandDTO", source = "entity.brandEntity")
    PriceDTO toDto(PriceEntity entity);

    @Mapping(target = "startDate", source = "startDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "endDate", source = "endDate", dateFormat = "yyyy-MM-dd HH:mm:ss")
    List<PriceDTO> toDto(List<PriceEntity> entities);

}

