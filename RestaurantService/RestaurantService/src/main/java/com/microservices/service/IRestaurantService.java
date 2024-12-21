package com.microservices.service;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;

import java.util.List;

public interface IRestaurantService {

    public List<RestaurantDTO> getAllRestaurants();

    public RestaurantDTO getRestaurantById(Long id);

    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO);

    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO);

    public void deleteRestaurant(Long id);

    public MenuItemDTO createMenuItem(Long restaurantId, MenuItemDTO menuItemDTO);

    public MenuItemDTO updateMenuItem(MenuItemDTO menuItemDTO);

    public void deleteMenuItem(Long id);

    public List<MenuItemDTO> getAllMenuItems(Long restaurantId);

}
