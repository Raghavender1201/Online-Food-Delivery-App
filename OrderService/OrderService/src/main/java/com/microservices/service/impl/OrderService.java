package com.microservices.service.impl;

import com.microservices.dto.OrderDTO;
import com.microservices.entity.MenuItemQuantity;
import com.microservices.entity.Order;
import com.microservices.mapper.OrderMapper;
import com.microservices.pojo.MenuItem;
import com.microservices.pojo.Restaurant;
import com.microservices.repository.OrderRepository;
import com.microservices.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private RestTemplate restTemplate;
    public OrderService(OrderRepository orderRepository, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Double totalAmount = 0.0;
        Restaurant restaurant = restTemplate.getForObject("http://localhost:8081/restaurants/" + orderDTO.getRestaurantId(), Restaurant.class);

        if (restaurant == null) {
            throw new IllegalArgumentException("Invalid restaurant ID");
        }

        List<MenuItemQuantity> menuItems = new ArrayList<>();
        for (MenuItemQuantity item : orderDTO.getMenuItemQuantity()) {
            Long menuItemId = item.getMenuItemId();
            Integer quantity = item.getQuantity();
            MenuItem menuItem = restaurant.getMenuItems().stream()
                    .filter(m -> m.getId().equals(menuItemId))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Invalid menu item ID"));

            menuItems.add(new MenuItemQuantity(menuItemId, quantity));
            totalAmount += menuItem.getPrice() * quantity;
        }

        orderDTO.setTotalAmount(totalAmount);
        orderDTO.setStatus("Received");
        orderDTO.setOrderDate(new Date());

        Order order = OrderMapper.mapToOrder(orderDTO);
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.mapToOrderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::mapToOrderDTO)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        return OrderMapper.mapToOrderDTO(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order not found");
        }
        orderRepository.deleteById(id);
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId).stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByRestaurantId(Long restaurantId) {
        return orderRepository.findByRestaurantId(restaurantId).stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        return orderRepository.findByStatus(status).stream()
                .map(OrderMapper::mapToOrderDTO)
                .collect(Collectors.toList());
    }
}
