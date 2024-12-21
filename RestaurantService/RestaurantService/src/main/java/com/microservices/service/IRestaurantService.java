package com.microservices.service;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;

import java.util.List;

public interface IRestaurantService {

    List<RestaurantDTO> getAllRestaurants();

    RestaurantDTO getRestaurantById(Long id);

    RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO);

    void deleteRestaurant(Long id);

    MenuItemDTO createMenuItem(Long restaurantId, MenuItemDTO menuItemDTO);

    MenuItemDTO updateMenuItem(MenuItemDTO menuItemDTO);

    void deleteMenuItem(Long id);

    List<MenuItemDTO> getAllMenuItems(Long restaurantId);

}
