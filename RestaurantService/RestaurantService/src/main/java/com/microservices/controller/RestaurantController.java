package com.microservices.controller;

import com.microservices.dto.ContactInfoDetails;
import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.RestaurantDTO;
import com.microservices.service.IRestaurantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurants")
@Validated
@Tag(name = "Restaurant Service to create, update, delete, get Restaurant Details and to add menu items",
    description = "Restaurant Service to Create, Update, Delete and Get Restaurant and Menu Item Details")
public class RestaurantController {

    private static final Logger logger = LoggerFactory.getLogger(RestaurantController.class);

    private IRestaurantService restaurantService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private ContactInfoDetails contactInfoDetails;


    public RestaurantController(IRestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Operation(
            summary = "Create Restaurant",
            description = "Create Restaurant"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Restaurant created successfully",
            content = @Content(
                    schema = @Schema(implementation = RestaurantDTO.class)
            )
    )
    @PostMapping
    public ResponseEntity<RestaurantDTO> createRestaurant(@Valid @RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO createdRestaurant = restaurantService.createRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRestaurant);
    }

    @Operation(
            summary = "Get All Restaurants",
            description = "Get All Restaurants"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurants found successfully",
            content = @Content(
                    schema = @Schema(implementation = RestaurantDTO.class)
            )
    )
    @GetMapping("/all")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurants() {
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }

    @Operation(
            summary = "Get Restaurant by Id",
            description = "Get Restaurant by Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurant found successfully",
            content = @Content(
                    schema = @Schema(implementation = RestaurantDTO.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id) {
        RestaurantDTO restaurant = restaurantService.getRestaurantById(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurant);
    }

    @Operation(
            summary = "Update Restaurant",
            description = "Update Restaurant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurant updated successfully",
            content = @Content(
                    schema = @Schema(implementation = RestaurantDTO.class)
            )
    )
    @PutMapping("/update")
    public ResponseEntity<RestaurantDTO> updateRestaurant(@Valid@RequestBody RestaurantDTO restaurantDTO) {
        RestaurantDTO updatedRestaurant = restaurantService.updateRestaurant(restaurantDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedRestaurant);
    }

    @Operation(
            summary = "Delete Restaurant",
            description = "Delete Restaurant"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Restaurant deleted successfully"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.status(HttpStatus.OK).body("Restaurant deleted successfully");
    }

    @Operation(
            summary = "Create Menu Item By Restaurant ID",
            description = "Create Menu Item Restaurant ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Menu Item created successfully",
            content = @Content(
                    schema = @Schema(implementation = MenuItemDTO.class)
            )
    )
    @PostMapping("/{restaurantId}/menu")
    public ResponseEntity<MenuItemDTO> createMenuItem(@PathVariable Long restaurantId, @Valid @RequestBody MenuItemDTO menuItemDTO) {
        MenuItemDTO createdMenuItem = restaurantService.createMenuItem(restaurantId, menuItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMenuItem);
    }

    @Operation(
            summary = "Get All Menu Items By Restaurant ID",
            description = "Get All Menu Items By Restaurant ID"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Menu Items found successfully",
            content = @Content(
                    schema = @Schema(implementation = MenuItemDTO.class)
            )
    )
    @GetMapping("/menu/{restaurantId}")
    public ResponseEntity<List<MenuItemDTO>> getAllMenuItems(@PathVariable Long restaurantId) {
        List<MenuItemDTO> menuItems = restaurantService.getAllMenuItems(restaurantId);
        return ResponseEntity.status(HttpStatus.OK).body(menuItems);
    }

    @Operation(
            summary = "Update Menu Item",
            description = "Update Menu Item"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Menu Item updated successfully",
            content = @Content(
                    schema = @Schema(implementation = MenuItemDTO.class)
            )
    )
    @PutMapping("/menu/update")
    public ResponseEntity<MenuItemDTO> updateMenuItem(@Valid @RequestBody MenuItemDTO menuItemDTO) {
        MenuItemDTO updatedMenuItem = restaurantService.updateMenuItem(menuItemDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedMenuItem);
    }

    @Operation(
            summary = "Delete Menu Item",
            description = "Delete Menu Item"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Menu Item deleted successfully"
    )
    @DeleteMapping("/menu/delete/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable Long id) {
        restaurantService.deleteMenuItem(id);
        return ResponseEntity.status(HttpStatus.OK).body("Menu item deleted successfully");
    }


    @PostMapping("/allRestaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurantId(@RequestHeader("CorrelationId") String correlationId,
                                                                  @RequestBody List<Long> ids) {
        logger.debug("CorrelationId in Order Service: {}", correlationId);
        List<RestaurantDTO> restaurants = restaurantService.getAllRestaurantsById(ids);
        return ResponseEntity.status(HttpStatus.OK).body(restaurants);
    }


    @GetMapping("/version")
    public String getVersion() {
        return "Build version is: " +buildVersion;
    }

    @GetMapping("/java-version")
    public String javaVersion() {
        return environment.getProperty("JAVA_HOME");
    }

    @GetMapping("/contact-details")
    public ContactInfoDetails contactDetails() {
        return contactInfoDetails;
    }

}
