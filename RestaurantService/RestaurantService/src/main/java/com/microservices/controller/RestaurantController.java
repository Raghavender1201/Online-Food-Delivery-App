package com.microservices.controller;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;
import com.microservices.service.IRestaurantService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    private IRestaurantService restaurantService;

    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant( @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO createdRestaurant = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @PutMapping("/update")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO updatedRestaurant = restaurantService.updateRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRestaurant);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.status(HttpStatus.OK).body("Restaurant deleted successfully");
    }

    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuItemDTO> createMenuItem(@PathVariable Long restaurantId, @RequestBody MenuItemDTO menuItemDTO) {
        MenuItemDTO createdMenuItem = restaurantService.createMenuItem(restaurantId, menuItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
    }

    @GetMapping("/menu/{restaurantId}")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems(@PathVariable Long restaurantId) {
        List<MenuItemDTO> menuItems = restaurantService.getAllMenuItems(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(menuItems);
    }

    @PutMapping("/menu/update")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        MenuItemDTO updatedMenuItem = restaurantService.updateMenuItem(menuItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMenuItem);
    }

    @DeleteMapping("/menu/delete/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        restaurantService.deleteMenuItem(id);
        return ResponseEntity.status(HttpStatus.OK).body("Menu item deleted successfully");
    }
}
