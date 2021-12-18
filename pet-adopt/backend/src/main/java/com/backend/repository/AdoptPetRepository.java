package com.backend.repository;

import com.backend.entity.AdoptPetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdoptPetRepository extends JpaRepository<AdoptPetEntity, Long> {

}
