package com.microservices.dto;

import com.microservices.entity.MenuItem;

import java.util.List;

public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
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
