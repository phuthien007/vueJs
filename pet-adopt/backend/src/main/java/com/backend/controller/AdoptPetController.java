package com.backend.controller;


import com.backend.dto.AdoptPetDto;
import com.backend.entity.AdoptPetEntity;
import com.backend.exception.BadRequestException;
import com.backend.mapper.AdoptPetMapper;
import com.backend.service.AdoptPetService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(allowedHeaders = "*", exposedHeaders = "*")
@RequestMapping(path = "/api/")
public class AdoptPetController {
    private final AdoptPetService AdoptPetSer;
    private final AdoptPetMapper AdoptPetMapper;

    public AdoptPetController(AdoptPetService AdoptPetSer, AdoptPetMapper AdoptPetMapper) {
        this.AdoptPetSer = AdoptPetSer;
        this.AdoptPetMapper = AdoptPetMapper;
    }

    // get all element in AdoptPet
    @GetMapping("adoptPet")
    public List<AdoptPetDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            HttpServletResponse response
    ) {
        Page<AdoptPetEntity> results = AdoptPetSer.getAllData(PageRequest.of(page, 10));
        response.addHeader("totalElements", String.valueOf(results.getTotalElements()));
        response.addHeader("totalPages", String.valueOf(results.getTotalPages()));
        response.addHeader("page", String.valueOf(results.getNumber()));
        response.addHeader("numberOfElements", String.valueOf(results.getNumberOfElements()));
        return results.stream().map(AdoptPetEntity -> AdoptPetMapper.toDTO(AdoptPetEntity))
                .collect(Collectors.toList());
    }

    // get element by id
    @GetMapping("adoptPet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdoptPetDto findDataById(@PathVariable Long id) {
        return AdoptPetMapper.toDTO(AdoptPetSer.findDataById(id));
    }

    // add a new element
    @PostMapping("adoptPet")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public AdoptPetDto addNewData(@Validated @RequestBody AdoptPetDto AdoptPet) {
        if (AdoptPet.getId() != null) {
            AdoptPet.setId(null);
        }
        if (AdoptPet.getPetId() == null || AdoptPet.getCustomerId() == null) {
            throw new BadRequestException("Invalid Input, value missing");
        }
        return AdoptPetMapper.toDTO(AdoptPetSer.addNewData(AdoptPetMapper.toEntity(AdoptPet)));
    }

    // update a element with id
    @PutMapping("adoptPet/{id}")
    public AdoptPetDto updateData(@PathVariable Long id, @Validated @RequestBody AdoptPetDto AdoptPet) {
        return AdoptPetMapper.toDTO(AdoptPetSer.updataData(id, AdoptPetMapper.toEntity(AdoptPet)));
    }

    // delete a element by id
    @DeleteMapping("adoptPet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteData(@PathVariable Long id) {
        return AdoptPetSer.deleteData(id);
    }

}
