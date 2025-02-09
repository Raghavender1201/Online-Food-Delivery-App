package com.microservices.controller;

import com.microservices.dto.OrderDTO;
import com.microservices.service.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@Validated
@Tag(name = "Order Service to order food, get order details and to update order status",
        description = "Order Service to Create, Update, Delete and Get Order Details")
public class OrderController {

    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(
            summary = "Create Order",
            description = "Order Food from Restaurants"
    )
    @ApiResponse(responseCode = "201",
            description = "Order created successfully",
            content = @Content(schema = @Schema(implementation = OrderDTO.class)))
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderDTO order = orderService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @Operation(
            summary = "Get Order By Id",
            description = "Get Order By Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order found successfully",
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class)
            )
    )
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @Operation(
            summary = "Update Order Status",
            description = "Update Order Status"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order status updated successfully",
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class)
            )
    )
    @PutMapping("/update/{id}/{status}")
    public ResponseEntity<OrderDTO> updateOrderStatus(@PathVariable Long id, @PathVariable String status) {
        OrderDTO updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    @Operation(
            summary = "Delete Order",
            description = "Delete Order"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Order deleted successfully"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Get All Orders",
            description = "Get All Orders"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders found successfully",
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class)
            )
    )
    @GetMapping("/all")
    public ResponseEntity<Iterable<OrderDTO>> getAllOrders() {
        Iterable<OrderDTO> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Get Orders By Customer Id",
            description = "Get Orders By Customer Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders found successfully",
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class)
            )
    )
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Iterable<OrderDTO>> getOrdersByCustomerId(@PathVariable Long customerId) {
        Iterable<OrderDTO> orders = orderService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Get Orders By Restaurant Id",
            description = "Get Orders By Restaurant Id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders found successfully",
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class)
            )
    )
    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Iterable<OrderDTO>> getOrdersByRestaurantId(@PathVariable Long restaurantId) {
        Iterable<OrderDTO> orders = orderService.getOrdersByRestaurantId(restaurantId);
        return ResponseEntity.ok(orders);
    }

    @Operation(
            summary = "Get Orders By Status",
            description = "Get Orders By Status"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Orders found successfully",
            content = @Content(
                    schema = @Schema(implementation = OrderDTO.class)
            )
    )
    @GetMapping("/status/{status}")
    public ResponseEntity<Iterable<OrderDTO>> getOrdersByStatus(@PathVariable String status) {
        Iterable<OrderDTO> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

}
