package com.spacebased.space;

import com.spacebased.model.Order;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OrderSpace {
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>(); // Simulacao do que seria um space (pode ser Redis)

    public void write(Order order) {
        orders.put(order.getId(), order);
        System.out.println("Pedido salvo no space: " + order);
    }

    public Order read(UUID id) {
        return orders.get(id);
    }

    public void delete(UUID id) {
        orders.remove(id);
    }
}
