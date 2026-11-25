package app.task3;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorShoeWarehouse {

    public static final List<String> SHOE_TYPES = List.of(
            "Nike", "Adidas", "Puma", "Reebok", "New Balance"
    );

    private final Queue<Order> orders = new LinkedList<>();
    private final int capacity;
    private final ExecutorService processingPool;

    public ExecutorShoeWarehouse(int capacity, int poolSize) {
        this.capacity = capacity;
        this.processingPool = Executors.newFixedThreadPool(poolSize);
    }

    public synchronized void receiveOrder(Order order) throws InterruptedException {
        while (orders.size() >= capacity) {
            wait();
        }
        orders.offer(order);
        notifyAll();

        processingPool.submit(() -> {
            try {
                fulfillOrder();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    private synchronized void fulfillOrder() throws InterruptedException {
        while (orders.isEmpty()) {
            wait();
        }
        Order order = orders.poll();
        System.out.println(Thread.currentThread().getName() + " fulfilled: " + order);
        notifyAll();
    }

    public void shutdown() throws InterruptedException {
        processingPool.shutdown();
        if (!processingPool.awaitTermination(10, TimeUnit.SECONDS)) {
            processingPool.shutdownNow();
        }
    }
}