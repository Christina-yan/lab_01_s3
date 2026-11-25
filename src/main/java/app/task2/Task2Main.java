package app.task2;

public class Task2Main {

    private static final int CAPACITY = 5;
    private static final int TOTAL_ORDERS = 15;
    private static final int CONSUMERS = 3;

    public static void main(String[] args) throws InterruptedException {
        ShoeWarehouse warehouse = new ShoeWarehouse(CAPACITY);

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= TOTAL_ORDERS; i++) {
                String type = ShoeWarehouse.SHOE_TYPES.get(i % ShoeWarehouse.SHOE_TYPES.size());
                Order order = new Order(i, type, 1 + i % 3);
                try {
                    warehouse.receiveOrder(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        }, "Producer");

        Thread[] consumers = new Thread[CONSUMERS];
        for (int c = 0; c < CONSUMERS; c++) {
            consumers[c] = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        warehouse.fulfillOrder();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }, "Consumer-" + (c + 1));
        }

        producer.start();
        for (Thread consumer : consumers) {
            consumer.start();
        }

        producer.join();
        for (Thread consumer : consumers) {
            consumer.join();
        }

        System.out.println("Task 2 finished.");
    }
}