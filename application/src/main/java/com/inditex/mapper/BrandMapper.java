package com.inditex.mapper;

import com.inditex.core.model.Brand;
import com.inditex.dto.BrandDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BrandMapper {
    @Mapping(target = "name", source = "dto.name")
    Brand toBrand(BrandDTO dto);
}
