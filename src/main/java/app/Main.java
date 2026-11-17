package app;

import app.srp.NotificationService;
import app.srp.OrderProcessor;
import app.ocp.Circle;
import app.ocp.ShapeDrawer;
import app.ocp.Square;
import app.lsp.Car;
import app.lsp.Bicycle;
import app.isp.SimplePrinter;
import app.isp.MultiFunctionDevice;
import app.dip.Computer;
import app.dip.HardDrive;
import app.dip.CloudStorage;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== 1. SRP: Единственная ответственность ===");
        NotificationService notifier = new NotificationService();
        OrderProcessor processor = new OrderProcessor(notifier);
        processor.processOrder();

        System.out.println("\n=== 2. OCP: Открытость/Закрытость ===");
        ShapeDrawer drawer = new ShapeDrawer();
        drawer.draw(new Circle());
        drawer.draw(new Square());

        System.out.println("\n=== 3. LSP: Подстановка Лисков ===");
        Car car = new Car();
        car.startEngine();
        Bicycle bike = new Bicycle();
        bike.pedal();

        System.out.println("\n=== 4. ISP: Разделение интерфейсов ===");
        SimplePrinter printer = new SimplePrinter();
        printer.print();
        MultiFunctionDevice mfd = new MultiFunctionDevice();
        mfd.print();
        mfd.scan();
        mfd.fax();

        System.out.println("\n=== 5. DIP: Инверсия зависимостей ===");
        Computer pc1 = new Computer(new HardDrive());
        pc1.saveToStorage();
        Computer pc2 = new Computer(new CloudStorage());
        pc2.saveToStorage();
    }
}