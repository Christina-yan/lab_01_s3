package app;

import exceptions.InvalidFigureParameterException;
import exceptions.InvalidFigureRecordException;
import geometry2d.Figure;
import geometry3d.Cylinder;
import storage.BinaryFigureStorage;
import storage.FigureStorage;
import storage.TextFigureStorage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Path textPath;
        Path binaryPath;

        if (args.length >= 2) {
            textPath = Path.of(args[0]);
            binaryPath = Path.of(args[1]);
        } else {
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Путь к входному текстовому файлу: ");
                textPath = Path.of(scanner.nextLine().strip());
                System.out.print("Путь к выходному бинарному файлу: ");
                binaryPath = Path.of(scanner.nextLine().strip());
            }
        }


        FigureStorage textStorage = new TextFigureStorage();
        List<Figure> figures;
        try {
            figures = textStorage.read(textPath);
        } catch (IOException e) {
            System.err.println("Ошибка чтения текстового файла: " + e.getMessage());
            return;
        } catch (InvalidFigureRecordException e) {
            System.err.println("Ошибка формата: " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка десериализации: " + e.getMessage());
            return;
        }

        System.out.println("Прочитано фигур: " + figures.size());


        for (Figure figure : figures) {
            System.out.printf("%s | area=%.4f | perimeter=%.4f%n",
                    figure, figure.area(), figure.perimeter());
        }


        if (figures.isEmpty()) {
            System.out.println("Список пуст — цилиндр не создаётся.");
        } else {
            Figure base = figures.get(new Random().nextInt(figures.size()));
            double height = 5.0;
            try {
                Cylinder cylinder = new Cylinder(base, height);
                System.out.printf("Основание: %s%n", cylinder.getBase());
                System.out.printf("Высота: %.4f%n", cylinder.getHeight());
                System.out.printf("Объём: %.4f%n", cylinder.volume());
            } catch (InvalidFigureParameterException e) {
                System.err.println("Не удалось создать цилиндр: " + e.getMessage());
            }
        }


        FigureStorage binaryStorage = new BinaryFigureStorage();
        try {
            Files.createDirectories(binaryPath.toAbsolutePath().getParent());
            binaryStorage.write(binaryPath, figures);
            System.out.println("Список сохранён в бинарный файл: " + binaryPath);
        } catch (IOException e) {
            System.err.println("Ошибка записи бинарного файла: " + e.getMessage());
        }
    }
}