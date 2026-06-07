package model;

public class Order {
    private int id;
    private String client;
    private String status;

    public Order(int id, String client, String status) {
        this.id = id;
        this.client = client;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        return "Pedido {id = " + id + ", cliente = ' " + client + " ' status = ' " + status + " ' }";
    }
}
