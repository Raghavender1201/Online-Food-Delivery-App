package com.microservices.dto;

import com.microservices.entity.Restaurant;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public class MenuItemDTO {

    private Long id;
    @Schema(description = "Name of the menu item", example = "Burger")
    @NotEmpty(message = "name should not be empty")
    @Size(min = 2, max=15, message = "Name should be between 2 and 15 characters")
    private String name;
   //@NotEmpty
    //@Max(value = 1000, message = "Price should be less than 1000")
    @Schema(description = "Price of the menu item", example = "100")
    private Double price;
    private RestaurantDTO restaurantDTO;

    public MenuItemDTO() {
    }

    public MenuItemDTO(Long id, String name, Double price, RestaurantDTO restaurantDTO) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.restaurantDTO = restaurantDTO;
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

    public RestaurantDTO getRestaurantDTO() {
        return restaurantDTO;
    }

    public void setRestaurantDTO(RestaurantDTO restaurantDTO) {
        this.restaurantDTO = restaurantDTO;
    }
}
