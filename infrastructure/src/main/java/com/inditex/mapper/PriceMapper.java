package com.inditex.mapper;

import com.inditex.dto.PriceDTO;
import com.inditex.entity.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceMapper {

    @Mapping(target = "brandId", source = "entity.brand.id")
    PriceDTO toDto(PriceEntity entity);

    List<PriceDTO> toDto(List<PriceEntity> entities);

}

