package space;

import model.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderSpace {
    private Map<Integer, Order> orders = new HashMap<>(); // Simulação do do que seria um space (Pode ser um redis)

    public void write(Order order) {
        orders.put(order.getId(), order);
        System.out.println("Pedido salvo no space: " + order);
    }

    public Order read(int id) {
        return orders.get(id);
    }

    public void delete(int id) {
        orders.remove(id);
    }
}
