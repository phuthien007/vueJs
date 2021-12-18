package com.backend.service;


import com.backend.entity.AdoptPetEntity;
import com.backend.exception.BadRequestException;
import com.backend.repository.AdoptPetRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdoptPetService {

    private final AdoptPetRepository AdoptPetRep;

    public AdoptPetService(AdoptPetRepository AdoptPetRep) {
        this.AdoptPetRep = AdoptPetRep;
    }

    @Cacheable(cacheNames = "adoptPet")
    public Page<AdoptPetEntity> getAllData(Pageable pageable) {
        return AdoptPetRep.findAll(pageable);
    }

    @Cacheable(cacheNames = "adoptPet")
    public AdoptPetEntity findDataById(Long id) throws BadRequestException {
        return AdoptPetRep.findById(id).
                orElseThrow(() -> new BadRequestException("Data not found by ID: " + id));
    }


    public AdoptPetEntity addNewData(AdoptPetEntity AdoptPet) {
        try {
            return AdoptPetRep.save(AdoptPet);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CachePut(cacheNames = "adoptPet")
    public AdoptPetEntity updataData(Long id, AdoptPetEntity newAdoptPet) {
        try {
            AdoptPetEntity oldAdoptPet = AdoptPetRep.getById(id);
            if (newAdoptPet.getAdoptDate() != null) {
                oldAdoptPet.setAdoptDate(newAdoptPet.getAdoptDate());
            }
            if (newAdoptPet.getCustomer() != null) {
                oldAdoptPet.setCustomer(newAdoptPet.getCustomer());
            }
            if (newAdoptPet.getPet() != null) {
                oldAdoptPet.setPet(newAdoptPet.getPet());
            }
            if (newAdoptPet.getDescription() != null) {
                oldAdoptPet.setDescription(newAdoptPet.getDescription());
            }
            if (newAdoptPet.getStatus() != null) {
                oldAdoptPet.setStatus(newAdoptPet.getStatus());
            }
            return AdoptPetRep.save(oldAdoptPet);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }


    @CacheEvict(cacheNames = "adoptPet", allEntries = true)
    public Map<String, Boolean> deleteData(Long id) {
        try {
            Map<String, Boolean> result = new HashMap<>();
            AdoptPetRep.deleteById(id);
            result.put("Deleted", Boolean.TRUE);
            return result;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
