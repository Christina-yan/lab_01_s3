package main.java.storage;

import main.java.exceptions.InvalidFigureRecordException;
import main.java.geometry2d.Circle;
import main.java.geometry2d.Figure;
import main.java.geometry2d.Rectangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TextFigureStorage implements FigureStorage {

    private static final String SEPARATOR = ";";
    private static final String TYPE_CIRCLE = "CIRCLE";
    private static final String TYPE_RECTANGLE = "RECTANGLE";

    @Override
    public List<Figure> read(Path path)
            throws IOException, InvalidFigureRecordException {
        List<Figure> figures = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.strip();
                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }
                try {
                    Figure figure = parseLine(trimmed, lineNumber);
                    figures.add(figure);
                } catch (InvalidFigureRecordException e) {
                    System.err.println("[TextFigureStorage] " + e.getMessage() + " — строка пропущена");
                }
            }
        }
        return figures;
    }

    private Figure parseLine(String line, int lineNumber) throws InvalidFigureRecordException {
        String[] parts = line.split(SEPARATOR);
        String type = parts[0].strip().toUpperCase(Locale.ROOT);

        try {
            switch (type) {
                case TYPE_CIRCLE -> {
                    if (parts.length != 2) {
                        throw new InvalidFigureRecordException(lineNumber, "Ожидалось 2 поля для CIRCLE, получено " + parts.length);
                    }
                    double radius = Double.parseDouble(parts[1].strip());
                    return new Circle(radius);
                }
                case TYPE_RECTANGLE -> {
                    if (parts.length != 3) {
                        throw new InvalidFigureRecordException(lineNumber, "Ожидалось 3 поля для RECTANGLE, получено " + parts.length);
                    }
                    double width = Double.parseDouble(parts[1].strip());
                    double height = Double.parseDouble(parts[2].strip());
                    return new Rectangle(width, height);
                }
                default -> throw new InvalidFigureRecordException(lineNumber, "Неизвестный тип фигуры: " + type);
            }
        } catch (NumberFormatException e) {
            throw new InvalidFigureRecordException(lineNumber, "Ошибка преобразования числа: " + e.getMessage(), e);
        } catch (RuntimeException e) {
            throw new InvalidFigureRecordException(lineNumber, "Ошибка создания фигуры: " + e.getMessage(), e);
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            for (Figure figure : figures) {
                writer.write(formatFigure(figure));
                writer.newLine();
            }
        }
    }

    private String formatFigure(Figure figure) {
        if (figure instanceof Circle circle) {
            return TYPE_CIRCLE + SEPARATOR + circle.getRadius();
        }
        if (figure instanceof Rectangle rectangle) {
            return TYPE_RECTANGLE + SEPARATOR + rectangle.getWidth()
                    + SEPARATOR + rectangle.getHeight();
        }
        throw new IllegalArgumentException("Неподдерживаемый тип фигуры: " + figure.getClass().getName());
    }
}