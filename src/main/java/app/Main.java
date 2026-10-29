package app;

import app.model.Book;
import app.model.SmsMessage;
import app.model.Visitor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 1. Чтение JSON через Gson
        List<Visitor> visitors;
        try {
            String json = Files.readString(Path.of("books.json"), StandardCharsets.UTF_8);
            Type listType = new TypeToken<List<Visitor>>() {}.getType();
            visitors = new Gson().fromJson(json, listType);
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла books.json: " + e.getMessage());
            return;
        }

        // Задание 1: Посетители и их количество
        System.out.println("=== ЗАДАНИЕ 1: Посетители ===");
        visitors.forEach(v -> System.out.println(v.getName() + " " + v.getSurname()));
        System.out.println("Всего посетителей: " + visitors.size() + "\n");

        // Задание 2: Книги без повторений
        System.out.println("=== ЗАДАНИЕ 2: Уникальные книги ===");
        List<Book> uniqueBooks = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .distinct() // Убирает дубликаты (работает благодаря @Data в Book)
                .collect(Collectors.toList());
        uniqueBooks.forEach(b -> System.out.println(b.getName() + " (" + b.getAuthor() + ")"));
        System.out.println("Всего уникальных книг: " + uniqueBooks.size() + "\n");

        // Задание 3: Сортировка по году издания
        System.out.println("=== ЗАДАНИЕ 3: Книги по году издания ===");
        List<Book> sortedBooks = uniqueBooks.stream()
                .sorted(Comparator.comparingInt(Book::getPublishingYear))
                .collect(Collectors.toList());
        sortedBooks.forEach(b -> System.out.println(b.getPublishingYear() + " - " + b.getName()));
        System.out.println();

        // Задание 4: Поиск автора "Jane Austen"
        System.out.println("=== ЗАДАНИЕ 4: Поиск Jane Austen ===");
        boolean hasAusten = visitors.stream()
                .flatMap(v -> v.getFavoriteBooks().stream())
                .anyMatch(b -> "Jane Austen".equalsIgnoreCase(b.getAuthor()));
        System.out.println("Есть ли книги Jane Austen в избранном: " + (hasAusten ? "ДА" : "НЕТ") + "\n");

        // Задание 5: Максимальное число книг
        System.out.println("=== ЗАДАНИЕ 5: Максимум книг у одного посетителя ===");
        int maxBooks = visitors.stream()
                .mapToInt(v -> v.getFavoriteBooks().size())
                .max()
                .orElse(0);
        System.out.println("Максимальное число книг: " + maxBooks + "\n");

        // Задание 6: Сообщения подписчикам
        System.out.println("=== ЗАДАНИЕ 6: SMS-сообщения ===");

        // Вычисляем среднее кол-во книг по всем посетителям
        double averageBooks = visitors.stream()
                .mapToInt(v -> v.getFavoriteBooks().size())
                .average()
                .orElse(0.0);
        System.out.println("Среднее число книг на посетителя: " + averageBooks);

        // Фильтруем подписчиков и создаем сообщения
        List<SmsMessage> messages = visitors.stream()
                .filter(Visitor::isSubscribed) // Только subscribed = true
                .map(v -> {
                    int count = v.getFavoriteBooks().size();
                    String text;
                    if (count > averageBooks) {
                        text = "you are a bookworm";
                    } else if (count < averageBooks) {
                        text = "read more";
                    } else {
                        text = "fine";
                    }
                    return new SmsMessage(v.getPhone(), text);
                })
                .collect(Collectors.toList());

        messages.forEach(m -> System.out.println("На номер " + m.getPhoneNumber() + ": " + m.getMessage()));
    }
}