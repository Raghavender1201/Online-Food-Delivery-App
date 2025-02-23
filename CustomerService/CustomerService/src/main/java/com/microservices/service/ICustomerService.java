package com.microservices.service;

import com.microservices.dto.CustomerDTO;
import com.microservices.dto.CustomerDashboardDTO;

import java.util.List;

public interface ICustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(int id);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(int id);

    List<CustomerDTO> getAllCustomers();

    CustomerDashboardDTO getCustomerDashboard(String correlationId, long id);

}
