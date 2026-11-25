package app;

import app.task1.Task1Main;
import app.task2.Task2Main;
import app.task3.Task3Main;

public class Main {

    public static void main(String[] args) throws Exception {
        String task = args.length > 0 ? args[0] : "";

        switch (task) {
            case "1" -> Task1Main.main(args);
            case "2" -> Task2Main.main(args);
            case "3" -> Task3Main.main(args);
            default -> System.out.println(
                    "Usage: java app.Main [1|2|3]\n" +
                            "  1 - Создание потоков\n" +
                            "  2 - Producer-Consumer (ручные потоки)\n" +
                            "  3 - ExecutorService"
            );
        }
    }
}