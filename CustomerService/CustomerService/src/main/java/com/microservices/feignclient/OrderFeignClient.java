package com.microservices.feignclient;

import com.microservices.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("orderservice")
public interface OrderFeignClient {

    @GetMapping("/order/customer/{customerId}")
    public ResponseEntity<Iterable<OrderDTO>> getOrdersByCustomerId(@RequestHeader("CorrelationId") String correlationId,
                                                                    @PathVariable Long customerId);
}
