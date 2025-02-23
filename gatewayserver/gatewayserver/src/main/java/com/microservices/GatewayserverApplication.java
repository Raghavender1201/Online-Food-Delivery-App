package com.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator onlineFoodOrderRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(
						p -> p.path("/onlinefoodorder/customer/**")
						.filters(f -> f.rewritePath("/onlinefoodorder/customer/(?<segment>.*)", "/${segment}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://CUSTOMERSERVICE"))
				.route(
						p -> p.path("/onlinefoodorder/restaurant/**")
								.filters(f -> f.rewritePath("/onlinefoodorder/restaurant/(?<segment>.*)", "/${segment}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://RESTAURANTSERVICE"))
				.route(
						p -> p.path("/onlinefoodorder/order/**")
								.filters(f -> f.rewritePath("/onlinefoodorder/order/(?<segment>.*)", "/${segment}")
										.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
								.uri("lb://ORDERSERVICE")).build();
	}

}
