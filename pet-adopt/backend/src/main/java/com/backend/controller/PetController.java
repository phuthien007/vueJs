package com.backend.controller;


import com.backend.dto.PetDto;
import com.backend.entity.PetEntity;
import com.backend.exception.BadRequestException;
import com.backend.mapper.PetMapper;
import com.backend.service.PetService;
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
public class PetController {

    private final PetService PetSer;
    private final com.backend.mapper.PetMapper PetMapper;

    public PetController(PetService PetSer, PetMapper PetMapper) {
        this.PetSer = PetSer;
        this.PetMapper = PetMapper;
    }

    // get all element in Pet
    @GetMapping("pet")
    public List<PetDto> findAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            HttpServletResponse response
    ) {
        Page<PetEntity> results = PetSer.getAllData(PageRequest.of(page, 10));
        response.addHeader("totalElements", String.valueOf(results.getTotalElements()));
        response.addHeader("totalPages", String.valueOf(results.getTotalPages()));
        response.addHeader("page", String.valueOf(results.getNumber()));
        response.addHeader("numberOfElements", String.valueOf(results.getNumberOfElements()));
        return results.stream().map(PetEntity -> PetMapper.toDTO(PetEntity))
                .collect(Collectors.toList());
    }

    // get element by id
    @GetMapping("pet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PetDto findDataById(@PathVariable Long id) {
        return PetMapper.toDTO(PetSer.findDataById(id));
    }

    // add a new element
    @PostMapping("pet")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public PetDto addNewData(@Validated @RequestBody PetDto Pet) {
        if (Pet.getId() != null) {
            Pet.setId(null);
        }
        if (Pet.getName() == null || Pet.getCode() == null
                || Pet.getCategoryId() == null ) {
            throw new BadRequestException("Invalid Input, value missing");
        }
        return PetMapper.toDTO(PetSer.addNewData(PetMapper.toEntity(Pet)));
    }

    // update a element with id
    @PutMapping("pet/{id}")
    public PetDto updateData(@PathVariable Long id, @Validated @RequestBody PetDto Pet) {
        return PetMapper.toDTO(PetSer.updateData(id, PetMapper.toEntity(Pet)));
    }

    // delete a element by id
    @DeleteMapping("pet/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteData(@PathVariable Long id) {
        return PetSer.deleteData(id);
    }

}
