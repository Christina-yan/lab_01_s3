package app.task3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Task3Main {

    private static final int CAPACITY = 5;
    private static final int TOTAL_ORDERS = 15;
    private static final int PROCESSING_POOL_SIZE = 3;
    private static final int SUBMIT_POOL_SIZE = 2;

    public static void main(String[] args) throws InterruptedException {
        ExecutorShoeWarehouse warehouse =
                new ExecutorShoeWarehouse(CAPACITY, PROCESSING_POOL_SIZE);

        ExecutorService submitPool = Executors.newFixedThreadPool(SUBMIT_POOL_SIZE);

        for (int i = 1; i <= TOTAL_ORDERS; i++) {
            final int id = i;
            submitPool.submit(() -> {
                String type = ExecutorShoeWarehouse.SHOE_TYPES
                        .get(id % ExecutorShoeWarehouse.SHOE_TYPES.size());
                Order order = new Order(id, type, 1 + id % 3);
                try {
                    warehouse.receiveOrder(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        submitPool.shutdown();
        if (!submitPool.awaitTermination(10, TimeUnit.SECONDS)) {
            submitPool.shutdownNow();
        }

        warehouse.shutdown();
        System.out.println("Task 3 finished.");
    }
}