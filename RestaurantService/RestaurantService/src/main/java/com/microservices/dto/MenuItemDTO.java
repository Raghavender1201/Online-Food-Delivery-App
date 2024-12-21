package com.microservices.dto;

import com.microservices.entity.Restaurant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class MenuItemDTO {

    private Long id;
    private String name;
    private Double price;
    private Restaurant restaurant;

    public MenuItemDTO() {
    }

    public MenuItemDTO(Long id, String name, Double price, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurant = restaurant;
    }

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
