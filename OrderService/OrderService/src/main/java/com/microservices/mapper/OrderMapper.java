package com.microservices.mapper;

import com.microservices.dto.OrderDTO;
import com.microservices.entity.Order;

public class OrderMapper {

    public static Order mapToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(orderDTO.getStatus());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setRestaurantId(orderDTO.getRestaurantId());
        order.setMenuItemQuantity(orderDTO.getMenuItemQuantity());
        return order;
    }

    public static OrderDTO mapToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotalAmount(order.getTotalAmount());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setRestaurantId(order.getRestaurantId());
        orderDTO.setMenuItemQuantity(order.getMenuItemQuantity());
        return orderDTO;
    }
}
