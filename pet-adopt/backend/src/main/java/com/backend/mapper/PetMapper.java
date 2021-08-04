package com.backend.mapper;


import com.backend.dto.PetDto;
import com.backend.entity.PetEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PetMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "adopter.name", target = "adopterName")
    public  PetDto toDTO(PetEntity e);

    @InheritInverseConfiguration
    public PetEntity toEntity(PetDto d);
}
