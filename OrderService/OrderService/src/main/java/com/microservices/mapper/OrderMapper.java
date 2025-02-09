package com.microservices.mapper;

import com.microservices.dto.MenuItemQuantityDTO;
import com.microservices.dto.OrderDTO;
import com.microservices.entity.MenuItemQuantity;
import com.microservices.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order mapToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(orderDTO.getStatus());
        order.setTotalAmount(orderDTO.getTotalAmount());
        order.setCustomerId(orderDTO.getCustomerId());
        order.setRestaurantId(orderDTO.getRestaurantId());
        if(orderDTO.getMenuItemQuantityDTO() != null){
            MenuItemQuantity menuItemQuantity = new MenuItemQuantity();
            List<MenuItemQuantity> menuItemQuantityList = new ArrayList<>();
            for(MenuItemQuantityDTO menuItemQuantityDTO : orderDTO.getMenuItemQuantityDTO()){
                menuItemQuantity.setId(menuItemQuantityDTO.getId());
                menuItemQuantity.setMenuItemId(menuItemQuantityDTO.getMenuItemId());
                menuItemQuantity.setQuantity(menuItemQuantityDTO.getQuantity());
                menuItemQuantityList.add(menuItemQuantity);
            }
           order.setMenuItemQuantity(menuItemQuantityList);
        }
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
        if(order.getMenuItemQuantity() != null){
            MenuItemQuantityDTO menuItemQuantityDTO = new MenuItemQuantityDTO();
            List<MenuItemQuantityDTO> menuItemQuantityDTOList = new ArrayList<>();
            for(MenuItemQuantity menuItemQuantity : order.getMenuItemQuantity()){
                menuItemQuantityDTO.setId(menuItemQuantity.getId());
                menuItemQuantityDTO.setMenuItemId(menuItemQuantity.getMenuItemId());
                menuItemQuantityDTO.setQuantity(menuItemQuantity.getQuantity());
                menuItemQuantityDTOList.add(menuItemQuantityDTO);
            }
            orderDTO.setMenuItemQuantityDTO(menuItemQuantityDTOList);
        }
        return orderDTO;
    }
}
