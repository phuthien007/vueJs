package com.backend.service;

import com.backend.entity.CategoryEntity;
import com.backend.exception.BadRequestException;
import com.backend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryService {

    private final CategoryRepository categoryRep;

    public CategoryService(CategoryRepository categoryRep){
        this.categoryRep = categoryRep;
    }

    public List<CategoryEntity> getAllData(){
        return categoryRep.findAll();
    }

    public CategoryEntity findDataById(Long id) throws BadRequestException {
        return categoryRep.findById(id).
                orElseThrow(() -> new BadRequestException("Data not found by ID: " + id));
    }

    public CategoryEntity addNewData(CategoryEntity category){
        try{
            return categoryRep.save(category);
        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public CategoryEntity updataData(Long id, CategoryEntity newCategory){
        try{
            CategoryEntity oldCategory = categoryRep.getById(id);
            if(newCategory.getName() != null){
                oldCategory.setName(newCategory.getName());
            }
            if(newCategory.getCode() != null){
                oldCategory.setCode(newCategory.getCode());
            }
            return categoryRep.save( oldCategory);
        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    public Map<String, Boolean> deleteData(Long id){
        try{
            Map<String, Boolean> result = new HashMap<>();
            categoryRep.deleteById(id);
            result.put("Deleted", Boolean.TRUE);
            return  result;
        }
        catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

}
