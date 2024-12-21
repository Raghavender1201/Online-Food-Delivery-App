package com.microservices.mapper;

import com.microservices.dto.MenuItemDTO;
import com.microservices.entity.MenuItem;

public class MenuItemMapper {

    public static MenuItemDTO mapToMenuItemDTO(MenuItem menuItem) {
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setId(menuItem.getId());
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setPrice(menuItem.getPrice());
        if(menuItem.getRestaurant() == null) return menuItemDTO;
        menuItemDTO.setRestaurant(menuItem.getRestaurant());
        return menuItemDTO;
    }

    public static MenuItem mapToMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemDTO.getId());
        menuItem.setName(menuItemDTO.getName());
        menuItem.setPrice(menuItemDTO.getPrice());
        if(menuItemDTO.getRestaurant() == null) return menuItem;
        menuItem.setRestaurant(menuItemDTO.getRestaurant());
        return menuItem;
    }
}
