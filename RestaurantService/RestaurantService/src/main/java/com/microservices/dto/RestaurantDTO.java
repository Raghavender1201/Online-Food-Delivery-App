package com.microservices.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class RestaurantDTO {
    private Long id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 5, max=30, message = "Name should be between 5 and 30 characters")
    @Schema(description = "Name of the restaurant", example = "McDonalds")
    private String name;
    @Schema(description = "Address of the restaurant", example = "123 Main St")
    @NotEmpty(message = "address should not be empty")
    @Size(min = 5, max=30, message = "Address should be between 5 and 30 characters")
    private String address;
    @Schema(description = "Menu items of the restaurant", example = "[{\"id\":1,\"name\":\"Burger\",\"price\":100,\"restaurantDTO\":null}]")
    private List<MenuItemDTO> menuItems;

    public RestaurantDTO(Long id, List<MenuItemDTO> menuItems, String address, String name) {
        this.id = id;
        this.menuItems = menuItems;
        this.address = address;
        this.name = name;
    }

    public RestaurantDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public List<MenuItemDTO> getMenuItems() {
        return menuItems;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMenuItems(List<MenuItemDTO> menuItems) {
        this.menuItems = menuItems;
    }
}
