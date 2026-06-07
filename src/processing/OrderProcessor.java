package processing;

import model.Order;
import repository.OrderRepository;
import space.OrderSpace;

public class OrderProcessor {
    private OrderRepository orderRepository;
    private OrderSpace orderSpace;

    public OrderProcessor(OrderSpace orderSpace, OrderRepository orderRepository) {
        this.orderSpace = orderSpace;
        this.orderRepository = orderRepository;
    }

    public void processOrder(int id) {
        Order order = orderSpace.read(id);

        if (order == null) {
            System.out.println("Pedido não encontrado.");
            return;
        }

        order.setStatus("PROCESSED");
        System.out.println("Pedido processado pela unidade de processamento (Processing Unit): " + order);
        orderRepository.saveOnDatabase(order);
    }
}
