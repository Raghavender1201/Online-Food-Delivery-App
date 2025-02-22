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
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@EnableConfigurationProperties(value = ContactInfoDetails.class)
@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Order Service REST API Documentation",
				version = "1.0",
				description = "Order Service For Online Food Delivery App",
				contact = @Contact(
						name = "Raghu",
						email = "raghupatel@gmail.com",
						url = "https://github.com/raghavender1201"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Order Service Documentation",
				url = "https://spring.io/blog/2015/07/14/microservices-with-spring"
		)
)
@EnableFeignClients
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
