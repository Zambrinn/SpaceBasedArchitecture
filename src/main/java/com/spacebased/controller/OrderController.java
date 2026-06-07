package com.spacebased.controller;

import com.spacebased.model.Order;
import com.spacebased.model.dto.OrderRequest;
import com.spacebased.model.dto.OrderResponse;
import com.spacebased.repository.OrderRepository;
import com.spacebased.service.OrderService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        Order order = orderService.createOrder(orderRequest);
        OrderResponse orderToSend = orderService.convertToDto(order);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderToSend);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.findAll());
    }

}
