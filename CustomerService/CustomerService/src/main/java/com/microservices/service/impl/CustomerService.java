package com.microservices.service.impl;

import com.microservices.dto.CustomerDTO;
import com.microservices.entity.Customer;
import com.microservices.exceptions.CustomerAlreadyExistException;
import com.microservices.exceptions.CustomerNotFoundException;
import com.microservices.mapper.CustomerMapper;
import com.microservices.repository.CustomerRepository;
import com.microservices.service.ICustomerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
}
