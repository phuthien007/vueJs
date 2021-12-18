package com.backend.mapper;

import com.backend.dto.AdoptPetDto;
import com.backend.entity.AdoptPetEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AdoptPetMapper {

    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "pet.id", target = "petId")
    public AdoptPetDto toDTO(AdoptPetEntity e);

    @InheritInverseConfiguration
    public AdoptPetEntity toEntity(AdoptPetDto d);

}
