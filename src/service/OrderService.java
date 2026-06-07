package service;

import model.Order;
import space.OrderSpace;

public class OrderService {
    private OrderSpace orderSpace;

    public OrderService(OrderSpace orderSpace) {
        this.orderSpace = orderSpace;
    }

    public void createOrder(Order order) {
        orderSpace.write(order);
    }
}
