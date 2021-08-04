package com.backend.dto;

import java.util.Date;

public class PetDto {
    private Long id;
    private String name;
    private String code;
    private String categoryName;
    private String image;
    private Date arriveredDate;
    private Date adoptedDate;
    private String adopterName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getArriveredDate() {
        return arriveredDate;
    }

    public void setArriveredDate(Date arriveredDate) {
        this.arriveredDate = arriveredDate;
    }

    public Date getAdoptedDate() {
        return adoptedDate;
    }

    public void setAdoptedDate(Date adoptedDate) {
        this.adoptedDate = adoptedDate;
    }

    public String getAdopterName() {
        return adopterName;
    }

    public void setAdopterName(String adopterName) {
        this.adopterName = adopterName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
