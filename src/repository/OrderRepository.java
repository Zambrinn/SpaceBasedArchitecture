package repository;

import model.Order;

public class OrderRepository {
    public void saveOnDatabase(Order order) {
        System.out.println("Pedido persistido no banco de dados: " + order);
    }
}
