package app.srp;

public class OrderProcessor {
    private final NotificationService notificationService;

    public OrderProcessor(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void processOrder() {
        System.out.println("Обработка заказа...");
        notificationService.sendNotification("Заказ успешно обработан!");
    }
}