package com.microservices.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


@ConfigurationProperties(prefix = "customer")
public record ContactInfoDetails(String message, Map<String, String> contactDetails) {
}
