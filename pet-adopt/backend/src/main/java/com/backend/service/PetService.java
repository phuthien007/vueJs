package com.backend.service;

import com.backend.entity.PetEntity;
import com.backend.exception.BadRequestException;
import com.backend.repository.PetRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PetService {

    private final PetRepository PetRep;

    public PetService(PetRepository PetRep) {
        this.PetRep = PetRep;
    }

    @Cacheable(cacheNames = "pet")
    public Page<PetEntity> getAllData(Pageable pageable) {
        return PetRep.findAll(pageable);
    }

    @Cacheable(cacheNames = "pet")
    public PetEntity findDataById(Long id) throws BadRequestException {
        return PetRep.findById(id).
                orElseThrow(() -> new BadRequestException("Data not found by ID: " + id));
    }

    public PetEntity addNewData(PetEntity Pet) {
        try {
            return PetRep.save(Pet);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CachePut(cacheNames = "pet")
    public PetEntity updateData(Long id, PetEntity newPet) {
        try {
            PetEntity oldPet = PetRep.getById(id);
            if (newPet.getName() != null) {
                oldPet.setName(newPet.getName());
            }
            if (newPet.getCode() != null) {
                oldPet.setCode(newPet.getCode());
            }
            if (newPet.getImage() != null) {
                oldPet.setImage(newPet.getImage());
            }

            if (newPet.getCategory() != null) {
                oldPet.setCategory(newPet.getCategory());
            }
            if (newPet.getStatus() != null) {
                oldPet.setStatus(newPet.getStatus());
            }
            return PetRep.save(oldPet);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @CacheEvict(cacheNames = "pet", allEntries = true)
    public Map<String, Boolean> deleteData(Long id) {
        try {
            Map<String, Boolean> result = new HashMap<>();
            PetRep.deleteById(id);
            result.put("Deleted", Boolean.TRUE);
            return result;
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}
