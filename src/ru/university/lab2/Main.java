package ru.university.lab2;

import java.util.Scanner;
import ru.university.lab2.numbers.IntegerTask;
import ru.university.lab2.floatpoint.FloatTask;
import ru.university.lab2.bitwise.BitwiseTask;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String menu = """
            Выберите задание:
            1 - Целочисленные ловушки
            2 - Вещественная арифметика
            3 - Побитовые операции
            4 - Строки
            5 - Одномерные массивы
            6 - Многомерные массивы
            7 - Методы и аргументы
            0 - Выход
            """;

        int choice;
        do {
            System.out.print(menu);
            System.out.print("Введите номер: ");

            //8.3
            if (!scanner.hasNextInt()) {
                System.out.println("Ошибка: введите целое число");
                scanner.next(); // очистка буфера
                choice = -1;
                continue;
            }

            choice = scanner.nextInt();

            // 8.2
            switch (choice) {
                case 1 -> new IntegerTask().run();
                case 2 -> new FloatTask().run();
                case 3 -> new BitwiseTask().run();
                case 0 -> System.out.println("Выход...");
                default -> System.out.println("Задания с таким номером не существует.");
            }
        } while (choice != 0);
    }
}