package com.microservices.feignclient;

import com.microservices.dto.RestaurantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("restaurantservice")
public interface RestaurantFeignClient {

    @GetMapping("/restaurants/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantById(@PathVariable Long id);
}
