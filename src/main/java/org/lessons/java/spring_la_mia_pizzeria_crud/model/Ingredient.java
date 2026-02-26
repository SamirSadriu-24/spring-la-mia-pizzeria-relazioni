package org.lessons.java.spring_la_mia_pizzeria_crud.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "il nome non può essere vuoto!")
    private String name;

    @ManyToMany(mappedBy = "ingredients", fetch = FetchType.EAGER)
    private Set<Pizza> pizzas = new HashSet<>();

    public void setPizzas(Set<Pizza> pizza) {
        this.pizzas = pizza;
    }

    public Set<Pizza> getPizzas() {
        return this.pizzas;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.id + this.name;
    }
}
