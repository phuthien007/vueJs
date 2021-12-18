package com.backend.mapper;


import com.backend.dto.CustomerDto;
import com.backend.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {


    @Mapping(source = "user.id", target = "userId")
    public CustomerDto toDTO(CustomerEntity e);

    @InheritInverseConfiguration
    public CustomerEntity toEntity(CustomerDto d);
}
