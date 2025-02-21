package com.microservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.List;

public class OrderDTO {

    private Long id;
    //@NotEmpty(message = "CustomerId should not be empty")
    @Schema(name = "customerId", example = "1")
    private Long customerId;
    @Schema(name = "restaurantId", example = "1")
    //@NotEmpty(message = "RestaurantId should not be empty")
    private Long restaurantId;
    @Schema(name = "menuItemQuantityDTO",
            description = "List of MenuItemQuantityDTO",
            example = "[{\"menuItemId\":1,\"quantity\":2},{\"menuItemId\":2,\"quantity\":3}]")
    @NotEmpty.List({
            @NotEmpty(message = "menuItemId should not be empty"),
            @NotEmpty(message = "quantity should not be empty")
    })
    private List<MenuItemQuantityDTO> menuItemQuantityDTO;
    private Double totalAmount;
    private String status;
    private Date orderDate;

    public OrderDTO() {}

    public OrderDTO(Long id, Long customerId, Long restaurantId, List<MenuItemQuantityDTO> menuItemQuantityDTO, Double totalAmount, String status, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.menuItemQuantityDTO = menuItemQuantityDTO;
        this.totalAmount = totalAmount;
        this.status = status;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public Long getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }
    public List<MenuItemQuantityDTO> getMenuItemQuantityDTO() {
        return menuItemQuantityDTO;
    }
    public void setMenuItemQuantityDTO(List<MenuItemQuantityDTO> menuItemQuantityDTO) {
        this.menuItemQuantityDTO = menuItemQuantityDTO;
    }
    public Double getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

}
