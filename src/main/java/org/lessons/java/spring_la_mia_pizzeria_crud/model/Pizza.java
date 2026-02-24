package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;


@Entity
@Table(name = "Pizzas")
public class Pizza {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "La pizza deve avere un nome!")
    private String name;
    
    @Lob
    @NotBlank(message = "La pizza deve contenere una descrizione")
    private String description;

    @NotBlank(message = "Inserire il link per la foto della pizza!")
    private String photoUrl;

    @DecimalMin(value = "1", message = "il prezzo deve essere almeno 1 euro")
    @DecimalMax(value = "99", message = "il prezzo deve essere di massimo 99 euro")
    private BigDecimal price;

    public void setId(Integer id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    
    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
    
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl(){
        return this.photoUrl;
    }

    
    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public BigDecimal getPrice(){
        return this.price;
    }

    @Override
    public String toString(){
        return this.name + this.description + this.photoUrl + this.price;
    }
    
}
