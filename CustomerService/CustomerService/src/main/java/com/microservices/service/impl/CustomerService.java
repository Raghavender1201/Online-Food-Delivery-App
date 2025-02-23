package com.microservices.service.impl;

import com.microservices.dto.*;
import com.microservices.entity.Customer;
import com.microservices.exceptions.CustomerAlreadyExistException;
import com.microservices.exceptions.CustomerNotFoundException;
import com.microservices.feignclient.OrderFeignClient;
import com.microservices.feignclient.RestaurantFeignClient;
import com.microservices.mapper.CustomerMapper;
import com.microservices.repository.CustomerRepository;
import com.microservices.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    private RestaurantFeignClient restaurantFeignClient;

    private OrderFeignClient orderFeignClient;

    public CustomerService(CustomerRepository customerRepository, RestaurantFeignClient restaurantFeignClient, OrderFeignClient orderFeignClient) {
        this.customerRepository = customerRepository;
        this.restaurantFeignClient = restaurantFeignClient;
        this.orderFeignClient = orderFeignClient;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        if(customerRepository.findByEmail(customerDTO.getEmail()).isPresent()){
            throw new CustomerAlreadyExistException("Customer Already exist with email " + customerDTO.getEmail());
        }

        Customer customer = CustomerMapper.mapToCustomer(customerDTO);
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Admin");
        Customer savedCustomer = customerRepository.save(customer);
        CustomerDTO savedCustomerDTO = CustomerMapper.mapToCustomerDTO(savedCustomer);
        return savedCustomerDTO;
    }

    @Override
    public CustomerDTO getCustomerById(int id) {

        return customerRepository.findById(id).map(CustomerMapper::mapToCustomerDTO).orElse(null);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerDTO.getId()).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found with id " + customerDTO.getId()));
        if (customer != null) {
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());
            Customer savedCustomer = customerRepository.save(customer);
            return CustomerMapper.mapToCustomerDTO(savedCustomer);
        }
        return null;
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found with id " + id));
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::mapToCustomerDTO).toList();
    }

    @Override
    public CustomerDashboardDTO getCustomerDashboard(String correlationId, long id) {
        Customer customer = customerRepository.findById((int) id).orElseThrow(() ->
                new CustomerNotFoundException("Customer not found with id " + id));
        if (customer != null) {
            Iterable<OrderDTO> iterableOrders = orderFeignClient.getOrdersByCustomerId(correlationId, id).getBody();
            List<OrderDTO> orders = StreamSupport.stream(iterableOrders.spliterator(), false)
                    .collect(Collectors.toList());

            List<Long> restaurantIds = orders.stream()
                    .map(OrderDTO::getRestaurantId)
                    .distinct()
                    .collect(Collectors.toList());
            List<RestaurantDTO> restaurants = restaurantFeignClient.getAllRestaurantId(correlationId, restaurantIds).getBody();
            Map<Long, RestaurantDTO> restaurantMap = restaurants.stream()
                    .collect(Collectors.toMap(RestaurantDTO::getId, r -> r));

            List<OrdersWithRestaurantDTO> orderWithRestaurantList = orders.stream()
                    .map(order -> new OrdersWithRestaurantDTO(order, restaurantMap.get(order.getRestaurantId())))
                    .collect(Collectors.toList());
            CustomerDashboardDTO dashboard = new CustomerDashboardDTO();
            dashboard.setId(customer.getId());
            dashboard.setName(customer.getName());
            dashboard.setEmail(customer.getEmail());
            dashboard.setAddress(customer.getAddress());
            dashboard.setOrders(orderWithRestaurantList);
            return dashboard;
        }
        return null;
    }
}
