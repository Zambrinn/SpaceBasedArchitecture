import model.Order;
import processing.OrderProcessor;
import repository.OrderRepository;
import service.OrderService;
import space.OrderSpace;

public class Main {
    public static void main(String[] args) {
        OrderSpace orderSpace = new OrderSpace();
        OrderRepository orderRepository = new OrderRepository();
        OrderProcessor orderProcessor = new OrderProcessor(orderSpace, orderRepository);
        OrderService orderService = new OrderService(orderSpace);

        Order order = new Order(1, "Thiago", "CREATED");
        orderService.createOrder(order);
        orderProcessor.processOrder(1);
    }
}
