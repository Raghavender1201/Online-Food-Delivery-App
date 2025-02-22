package com.microservices.service.impl;

import com.microservices.dto.MenuItemDTO;
import com.microservices.dto.MenuItemQuantityDTO;
import com.microservices.dto.OrderDTO;
import com.microservices.dto.RestaurantDTO;
import com.microservices.entity.MenuItemQuantity;
import com.microservices.entity.Order;
import com.microservices.exception.MenuItemNotFoundException;
import com.microservices.exception.OrderNotFoundException;
import com.microservices.exception.RestaurantNotFoundException;
import com.microservices.feignclient.RestaurantFeignClient;
import com.microservices.mapper.OrderMapper;
import com.microservices.pojo.MenuItem;
import com.microservices.pojo.Restaurant;
import com.microservices.repository.OrderRepository;
import com.microservices.service.IOrderService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    private OrderRepository orderRepository;
    private RestaurantFeignClient restaurantFeignClient;
    public OrderService(OrderRepository orderRepository, RestaurantFeignClient restaurantFeignClient) {
        this.orderRepository = orderRepository;
        this.restaurantFeignClient = restaurantFeignClient;
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        Double totalAmount = 0.0;
        //Restaurant restaurant = restTemplate.getForObject("http://localhost:8081/restaurants/" + orderDTO.getRestaurantId(), Restaurant.class);
        RestaurantDTO restaurant = restaurantFeignClient.getRestaurantById(orderDTO.getRestaurantId()).getBody();

        if (restaurant == null) {
            throw new RestaurantNotFoundException("Restaurant not found with ID: " + orderDTO.getRestaurantId());
        }

        List<MenuItemQuantity> menuItems = new ArrayList<>();
        for (MenuItemQuantityDTO item : orderDTO.getMenuItemQuantityDTO()) {
            Long menuItemId = item.getMenuItemId();
            Integer quantity = item.getQuantity();
            MenuItemDTO menuItem = restaurant.getMenuItems().stream()
                    .filter(m -> m.getId().equals(menuItemId))
                    .findFirst()
                    .orElseThrow(() -> new MenuItemNotFoundException("Menu item not found with ID: " + menuItemId));

            menuItems.add(new MenuItemQuantity(menuItemId, quantity));
            totalAmount += menuItem.getPrice() * quantity;
        }

        orderDTO.setTotalAmount(totalAmount);
        orderDTO.setStatus("Received");
        orderDTO.setOrderDate(new Date());

        Order order = OrderMapper.mapToOrder(orderDTO);
        order.setCreatedAt(LocalDateTime.now());
        order.setCreatedBy("Admin");
        Order savedOrder = orderRepository.save(order);

        return OrderMapper.mapToOrderDTO(savedOrder);
    }

    @Override
    public OrderDTO getOrderById(Long id) {
        return orderRepository.findById(id)
                .map(OrderMapper::mapToOrderDTO)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
    }

    @Override
    public OrderDTO updateOrderStatus(Long id, String status) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new OrderNotFoundException("Order not found with ID: " + id));
        order.setStatus(status);
        return OrderMapper.mapToOrderDTO(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new OrderNotFoundException("Order not found with ID: " + id);
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
