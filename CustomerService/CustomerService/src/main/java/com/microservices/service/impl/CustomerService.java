package com.microservices.service.impl;

import com.microservices.dto.CustomerDTO;
import com.microservices.entity.Customer;
import com.microservices.mapper.CustomerMapper;
import com.microservices.repository.CustomerRepository;
import com.microservices.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        Customer customer = CustomerMapper.mapToCustomer(customerDTO);
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
        Customer customer = customerRepository.findById(customerDTO.getId()).orElse(null);
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
        customerRepository.deleteById(id);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::mapToCustomerDTO).toList();
    }
}
