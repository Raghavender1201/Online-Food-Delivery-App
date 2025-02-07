package com.microservices.pojo;

import java.util.List;


public class Restaurant {

    private Long id;
    private String name;
    private String address;
    private List<MenuItem> menuItems;

    public Restaurant(Long id, List<MenuItem> menuItems, String address, String name) {
        this.id = id;
        this.menuItems = menuItems;
        this.address = address;
        this.name = name;
    }
    public Restaurant() {}

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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public List<MenuItem> getMenuItems() {
        return menuItems;
    }
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}
