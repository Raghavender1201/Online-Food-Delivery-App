package com.microservices.service.impl;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;
import com.microservices.entity.MenuItem;
import com.microservices.entity.Restaurant;
import com.microservices.mapper.MenuItemMapper;
import com.microservices.mapper.RestaurantMapper;
import com.microservices.repository.MenuRepository;
import com.microservices.repository.RestaurantRepository;
import com.microservices.service.IRestaurantService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService implements IRestaurantService {

    private RestaurantRepository restaurantRepository;

    private MenuRepository menuRepository;

    public RestaurantService(RestaurantRepository restaurantRepository, MenuRepository menuRepository) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(RestaurantMapper::mapToRestaurantDTO).toList();
    }

    @Override
    public RestaurantDTO getRestaurantById(Long id) {
        RestaurantDTO restaurantDTO = RestaurantMapper
                                        .mapToRestaurantDTO(restaurantRepository.findById(id).orElse(null));
        return restaurantDTO;
    }

    @Override
    public RestaurantDTO createRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = RestaurantMapper.mapToRestaurant(restaurantDTO);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        RestaurantDTO savedRestaurantDTO = RestaurantMapper.mapToRestaurantDTO(savedRestaurant);
        return savedRestaurantDTO;
    }

    @Override
    public RestaurantDTO updateRestaurant(RestaurantDTO restaurantDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantDTO.getId()).orElse(null);
        if (restaurant != null) {
            restaurant.setName(restaurantDTO.getName());
            restaurant.setAddress(restaurantDTO.getAddress());
            return RestaurantMapper.mapToRestaurantDTO(restaurantRepository.save(restaurant));
        }
        return null;
    }

    @Override
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public MenuItemDTO createMenuItem(Long restaurantId, MenuItemDTO menuItemDTO) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with ID " + restaurantId + " not found."));
        boolean itemExists = restaurant.getMenuItems().stream()
                .anyMatch(item -> item.getName().equalsIgnoreCase(menuItemDTO.getName()));

        if (itemExists) {
            throw new RuntimeException("Menu item with name '" + menuItemDTO.getName() + "' already exists for this restaurant.");
        }
        MenuItem menuItem = MenuItemMapper.mapToMenuItem(menuItemDTO);
        menuItem.setRestaurant(restaurant);
        MenuItem savedMenuItem = menuRepository.save(menuItem);

        return MenuItemMapper.mapToMenuItemDTO(savedMenuItem);
    }

    @Override
    public MenuItemDTO updateMenuItem(MenuItemDTO menuItemDTO) {
        MenuItem menuItem = menuRepository.findById(menuItemDTO.getId())
                .orElseThrow(() -> new RuntimeException("Menu item with ID " + menuItemDTO.getId() + " not found."));

        menuItem.setName(menuItemDTO.getName());
        menuItem.setPrice(menuItemDTO.getPrice());

        return MenuItemMapper.mapToMenuItemDTO(menuRepository.save(menuItem));
    }

    @Override
    public void deleteMenuItem(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new RuntimeException("Menu item with ID " + id + " not found.");
        }
        menuRepository.deleteById(id);
    }

    @Override
    public List<MenuItemDTO> getAllMenuItems(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with ID " + restaurantId + " not found."));

        List<MenuItemDTO> menuItems = restaurant.getMenuItems().stream()
                .map(MenuItemMapper::mapToMenuItemDTO)
                .collect(Collectors.toList());

        if (menuItems.isEmpty()) {
            throw new RuntimeException("No menu items present for restaurant ID " + restaurantId);
        }
        return menuItems;
    }
}
