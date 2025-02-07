package com.microservices.service;

import com.microservices.dto.OrderDTO;

import java.util.Date;
import java.util.List;

public interface IOrderService {

    public OrderDTO createOrder(OrderDTO orderDTO);

    public OrderDTO getOrderById(Long id);

    public OrderDTO updateOrderStatus(Long id, String status);

    public void deleteOrder(Long id);

    public List<OrderDTO> getAllOrders();

    public List<OrderDTO> getOrdersByCustomerId(Long customerId);

    public List<OrderDTO> getOrdersByRestaurantId(Long restaurantId);

    public List<OrderDTO> getOrdersByStatus(String status);




}
