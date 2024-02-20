package com.inditex.mapper;

import com.inditex.dto.BrandDTO;
import com.inditex.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper2 {
    BrandDTO toDTO(BrandEntity entity);

    BrandEntity toEntity(BrandDTO dto);
}
