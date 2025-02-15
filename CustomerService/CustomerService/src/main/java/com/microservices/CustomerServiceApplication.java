package com.microservices;

import com.microservices.dto.ContactInfoDetails;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(info = @Info(
		title = "Customer Service Rest API Documentation",
		description = "This is a Customer Service Rest API Documentation for Online Food Delivery App",
		version = "1.0",
		contact = @Contact(
							name = "Raghu",
							email = "raghupatel@gmail.com",
							url = "https://github.com/raghavender1201"
		),
		license = @License(
				name = "Apache 2.0",
				url = "Open Source"
		)
),
		externalDocs = @ExternalDocumentation(
				description = "Customer Service Documentation",
				url = "https://spring.io/blog/2015/07/14/microservices-with-spring"
		)
)
@EnableConfigurationProperties(value = ContactInfoDetails.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}

}
