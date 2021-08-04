package com.backend.mapper;

import com.backend.dto.CategoryDto;
import com.backend.dto.CustomerDto;
import com.backend.entity.CategoryEntity;
import com.backend.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    public CategoryDto toDTO(CategoryEntity e);
    public CategoryEntity toEntity(CategoryDto d);
}
