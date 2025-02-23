package com.microservices.feignclient;

import com.microservices.dto.RestaurantDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("restaurantservice")
public interface RestaurantFeignClient {

    @PostMapping("/restaurants/allRestaurants")
    public ResponseEntity<List<RestaurantDTO>> getAllRestaurantId(@RequestHeader("CorrelationId") String correlationId,
                                                                  @RequestBody List<Long> ids);
}
