package com.backend.mapper;


import com.backend.dto.CustomerDto;
import com.backend.entity.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    public CustomerDto toDTO(CustomerEntity e);
    public CustomerEntity toEntity(CustomerDto d);
}
