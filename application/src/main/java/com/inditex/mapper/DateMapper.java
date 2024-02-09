package com.inditex.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Mapper(componentModel = "spring")
public interface DateMapper {
    @Named("mapToLocalDateTime")
    default OffsetDateTime mapToLocalDateTime(LocalDateTime value) {
        if (value != null) {
            return value.atOffset(ZoneOffset.UTC);
        } else {
            return null;
        }
    }

}