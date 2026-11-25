package app.task2;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ShoeWarehouse {

    public static final List<String> SHOE_TYPES = List.of(
            "Nike", "Adidas", "Puma", "Reebok", "New Balance"
    );

    private final Queue<Order> orders = new LinkedList<>();
    private final int capacity;

    public ShoeWarehouse(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void receiveOrder(Order order) throws InterruptedException {
        while (orders.size() >= capacity) {
            wait();
        }
        orders.offer(order);
        System.out.println("Received: " + order);
        notifyAll();
    }

    public synchronized Order fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        Order order = orders.poll();
        System.out.println("Fulfilled: " + order);
        notifyAll();
        return order;
    }
}