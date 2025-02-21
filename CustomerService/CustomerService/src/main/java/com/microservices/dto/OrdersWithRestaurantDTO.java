package com.microservices.dto;

public class OrdersWithRestaurantDTO {

    private OrderDTO order;
    private RestaurantDTO restaurant;

    public OrdersWithRestaurantDTO(OrderDTO order, RestaurantDTO restaurant) {
        this.order = order;
        this.restaurant = restaurant;
    }

    public OrdersWithRestaurantDTO() {

    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public RestaurantDTO getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(RestaurantDTO restaurant) {
        this.restaurant = restaurant;
    }
}


