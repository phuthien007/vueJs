package com.backend.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="pet")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name="category_id", referencedColumnName = "id")
    private CategoryEntity category;
    private String name;
    private String code;

    private String image;

    @Column(name="arrivered_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date arriveredDate;

    @Column(name="adopted_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adoptedDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JoinColumn(name="adopter_id", referencedColumnName = "id")
    private CustomerEntity adopter;

    private String description;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
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

    public CustomerEntity getAdopter() {
        return adopter;
    }

    public void setAdopter(CustomerEntity adopter) {
        this.adopter = adopter;
    }
}
