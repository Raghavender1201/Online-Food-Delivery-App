package com.microservices.service;

import com.microservices.dto.CustomerDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(int id);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(int id);

    List<CustomerDTO> getAllCustomers();

}
