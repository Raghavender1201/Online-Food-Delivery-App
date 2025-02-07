package com.microservices.dto;

import com.microservices.entity.MenuItemQuantity;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderDTO {

    private Long id;
    private Long customerId;
    private Long restaurantId;
    private List<MenuItemQuantity> menuItemQuantity;
    private Double totalAmount;
    private String status;
    private Date orderDate;

    public OrderDTO() {}

    public OrderDTO(Long id, Long customerId, Long restaurantId, List<MenuItemQuantity> menuItemQuantity, Double totalAmount, String status, Date orderDate) {
        this.id = id;
        this.customerId = customerId;
        this.restaurantId = restaurantId;
        this.menuItemQuantity = menuItemQuantity;
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
    public List<MenuItemQuantity> getMenuItemQuantity() {
        return menuItemQuantity;
    }
    public void setMenuItemQuantity(List<MenuItemQuantity> menuItemQuantity) {
        this.menuItemQuantity = menuItemQuantity;
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
