package com.backend.mapper;

import com.backend.dto.CustomerDto;
import com.backend.dto.UserDto;
import com.backend.entity.CustomerEntity;
import com.backend.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "customer.id", target = "customerId")
    public UserDto toDTO(UserEntity e);

    @InheritInverseConfiguration
    public UserEntity toEntity(UserDto d);
}
