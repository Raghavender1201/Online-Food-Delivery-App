package com.microservices.mapper;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;
import com.microservices.entity.MenuItem;
import com.microservices.entity.Restaurant;

public class MenuItemMapper {

    public static MenuItemDTO mapToMenuItemDTO(MenuItem menuItem) {
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setId(menuItem.getId());
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setPrice(menuItem.getPrice());
        if(menuItem.getRestaurant() == null) {
            return menuItemDTO;
        }else{
            RestaurantDTO dto = new RestaurantDTO();
            Restaurant restaurant = menuItem.getRestaurant();
            dto.setId(restaurant.getId());
            dto.setName(restaurant.getName());
            dto.setAddress(restaurant.getAddress());
            menuItemDTO.setRestaurantDTO(dto);
        }
        return menuItemDTO;
    }

    public static MenuItem mapToMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(menuItemDTO.getId());
        menuItem.setName(menuItemDTO.getName());
        menuItem.setPrice(menuItemDTO.getPrice());
        if(menuItemDTO.getRestaurantDTO() == null) {
            return menuItem;
        }else{
            Restaurant restaurant = new Restaurant();
            RestaurantDTO dto = menuItemDTO.getRestaurantDTO();
            restaurant.setId(dto.getId());
            restaurant.setName(dto.getName());
            restaurant.setAddress(dto.getAddress());
            menuItem.setRestaurant(restaurant);
        }
        return menuItem;
    }
}
