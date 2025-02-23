package com.microservices.controller;

import com.microservices.dto.ContactInfoDetails;
import com.microservices.dto.CustomerDTO;
import com.microservices.dto.CustomerDashboardDTO;
import com.microservices.service.ICustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@Validated
@Tag(name = "Customer Service",
        description = "Customer Service to Register, Update, Delete and Get Customer Details")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private ICustomerService customerService;

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private ContactInfoDetails contactInfoDetails;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }


    @Operation(
            summary = "Register Customer",
            description = "Register Customer"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Customer created successfully",
            content = @Content(
                    schema = @Schema(implementation = CustomerDTO.class)
    )
    )
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO customer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(customer);
    }

    @Operation(
            summary = "Get Customer By Id",
            description = "Get Customer By Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer found successfully"
    )
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable int id) {
        CustomerDTO customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @Operation(
            summary = "Get All Customers",
            description = "Get All Customers"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customers found successfully"
    )
    @GetMapping("/all")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @Operation(
            summary = "Delete Customer",
            description = "Delete Customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer deleted successfully"
    )
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }

    @Operation(
            summary = "Update Customer",
            description = "Update Customer"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Customer updated successfully",
            content = @Content(
                    schema = @Schema(implementation = CustomerDTO.class)
            )
    )
    @PutMapping("/update")
    public ResponseEntity<CustomerDTO> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping("/customer-dashboard/{id}")
    public ResponseEntity<CustomerDashboardDTO> getCustomerDashboard(@RequestHeader("CorrelationId") String correlationId,
                                                                     @PathVariable long id) {
        logger.debug("CorrelationId in Customer Service: {}", correlationId);
        CustomerDashboardDTO customer = customerService.getCustomerDashboard(correlationId, id);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/version")
    public String getVersion() {
        return "Build version is: " +buildVersion;
    }

    @GetMapping("/java-version")
    public String javaVersion() {
        return environment.getProperty("JAVA_HOME");
    }

    @GetMapping("/contact-details")
    public ContactInfoDetails contactDetails() {
        return contactInfoDetails;
    }


}
