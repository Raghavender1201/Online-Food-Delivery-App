package com.microservices.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Customer",
        description = "Customer details",
        example = "\"{'name':'John Doe','email':'p0M6i@example.com','address':'123 Main St''}\""
)
public class CustomerDTO {

    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max=30, message = "Name should be between 5 and 30 characters")
    @Schema(
            name = "name",
            description = "Name of the customer",
            example = "John Doe"
    )
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email
    @Schema(
            name = "email",
            description = "Email of the customer",
            example = "p0M6i@example.com"
    )
    private String email;
    @NotEmpty(message = "Address should not be empty")
    @Size(min = 5, max=30, message = "Address should be between 5 and 30 characters")
    @Schema(
            name = "address",
            description = "Address of the customer",
            example = "123 Main St"
    )
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
