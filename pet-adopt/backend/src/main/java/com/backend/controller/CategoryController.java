package com.backend.controller;

import com.backend.dto.CategoryDto;
import com.backend.exception.BadRequestException;
import com.backend.mapper.CategoryMapper;
import com.backend.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/")
public class CategoryController {

    private final CategoryService categorySer;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categorySer, CategoryMapper categoryMapper){
        this.categorySer = categorySer;
        this.categoryMapper = categoryMapper;
    }

    // get all element in category
    @GetMapping("category")
    public List<CategoryDto> findAll(){
        return categorySer.getAllData().stream().
                map(category -> categoryMapper.toDTO(category)).collect(Collectors.toList());
    }

    // get element by id
    @GetMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDto findDataById(@PathVariable Long id){
        return categoryMapper.toDTO(categorySer.findDataById(id));
    }
    // add a new element
    @PostMapping("category")
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDto addNewData(@Validated @RequestBody CategoryDto category){
        if(category.getId() != null){
            category.setId(null);
        }
        if(category.getName() == null || category.getCode() == null){
            throw  new BadRequestException("Invalid Input, value missing");
        }
        return categoryMapper.toDTO(categorySer.addNewData(categoryMapper.toEntity(category)));
    }
    // update a element with id
    @PutMapping("category/{id}")
    public CategoryDto updateData(@PathVariable Long id ,@Validated @RequestBody CategoryDto category){
        return categoryMapper.toDTO(categorySer.updataData(id, categoryMapper.toEntity(category)));
    }
    // delete a element by id
    @DeleteMapping("category/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Boolean> deleteData(@PathVariable Long id){
        return categorySer.deleteData(id);
    }

}
