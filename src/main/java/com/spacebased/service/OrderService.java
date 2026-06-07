package com.spacebased.service;

import com.spacebased.model.Order;
import com.spacebased.model.OrderStatus;
import com.spacebased.model.dto.OrderRequest;
import com.spacebased.model.dto.OrderResponse;
import com.spacebased.processing.OrderProcessor;
import com.spacebased.repository.OrderRepository;
import com.spacebased.space.OrderSpace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderSpace orderSpace;
    @Autowired
    private OrderProcessor orderProcessor;
    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(OrderRequest orderRequest) {
        LocalDateTime now = LocalDateTime.now();

        Order order = Order.builder()
                .id(UUID.randomUUID())
                .clientName(orderRequest.clientName())
                .totalAmount(orderRequest.totalAmount())
                .status(OrderStatus.CREATED)
                .createdAt(now)
                .updatedAt(now)
                .build();

        System.out.println("SERVICE - Criando pedido"
                + " | orderId=" + order.getId()
                + " | status=" + order.getStatus()
                + " | thread=" + Thread.currentThread().getName());

        orderSpace.write(order);

        orderProcessor.processOrder(order.getId());

        System.out.println("SERVICE - Processor chamado de forma assincrona"
                + " | orderId=" + order.getId()
                + " | thread=" + Thread.currentThread().getName());

        return order;
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public OrderResponse convertToDto(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getClientName(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getCreatedAt()
        );
    }
}