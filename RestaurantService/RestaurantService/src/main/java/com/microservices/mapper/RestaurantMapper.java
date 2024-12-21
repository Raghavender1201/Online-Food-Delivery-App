package com.microservices.mapper;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;
import com.microservices.entity.MenuItem;
import com.microservices.entity.Restaurant;

import java.util.List;
import java.util.stream.Collectors;

public class RestaurantMapper {

    public static Restaurant mapToRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantDTO.getId());
        restaurant.setName(restaurantDTO.getName());
        restaurant.setAddress(restaurantDTO.getAddress());
        if(restaurantDTO.getMenuItems() == null) return restaurant;
        List<MenuItem> menuItems = restaurantDTO.getMenuItems().stream().map(MenuItemMapper::mapToMenuItem).collect(Collectors.toList());
        restaurant.setMenuItems(menuItems);
        return restaurant;
    }

    public static RestaurantDTO mapToRestaurantDTO(Restaurant restaurant) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        restaurantDTO.setId(restaurant.getId());
        restaurantDTO.setName(restaurant.getName());
        restaurantDTO.setAddress(restaurant.getAddress());
        if(restaurant.getMenuItems() == null) return restaurantDTO;
        List<MenuItemDTO> menuItems = restaurant.getMenuItems().stream().map(MenuItemMapper::mapToMenuItemDTO).collect(Collectors.toList());
        restaurantDTO.setMenuItems(menuItems);
        return restaurantDTO;
    }
}
