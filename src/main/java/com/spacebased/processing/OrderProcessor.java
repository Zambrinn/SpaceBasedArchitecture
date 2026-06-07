package com.spacebased.processing;

import com.spacebased.model.Order;
import com.spacebased.model.OrderStatus;
import com.spacebased.repository.OrderRepository;
import com.spacebased.space.OrderSpace;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderProcessor {
    private final OrderRepository orderRepository;
    private final OrderSpace orderSpace;

    public OrderProcessor(OrderRepository orderRepository, OrderSpace orderSpace) {
        this.orderRepository = orderRepository;
        this.orderSpace = orderSpace;
    }

    @Async("orderProcessorExecutor")
    public void processOrder(UUID id) {
        System.out.println("PROCESSOR - Iniciando processamento assincrono"
                + " | orderId=" + id
                + " | thread=" + Thread.currentThread().getName());

        Order order = orderSpace.read(id);

        if (order == null) {
            System.out.println("PROCESSOR - Pedido nao encontrado no Space"
                    + " | orderId=" + id
                    + " | thread=" + Thread.currentThread().getName());
            return;
        }

        order.updateStatus(OrderStatus.PROCESSING);

        System.out.println("PROCESSOR - Pedido em processamento"
                + " | orderId=" + order.getId()
                + " | status=" + order.getStatus()
                + " | thread=" + Thread.currentThread().getName());

        simulateBusinessProcessing(); // método com um "sleep" para demonstrar de melhor maneira o "processamento" do pedido

        order.updateStatus(OrderStatus.PROCESSED);

        orderRepository.save(order);
        orderSpace.delete(id);

        System.out.println("PROCESSOR - Pedido processado, persistido e removido do Space"
                + " | orderId=" + order.getId()
                + " | status=" + order.getStatus()
                + " | thread=" + Thread.currentThread().getName());
    }

    private void simulateBusinessProcessing() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Order processing was interrupted", exception);
        }
    }
}