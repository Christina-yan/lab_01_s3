package main.java.storage;

import main.java.geometry2d.Figure;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Бинарная реализация хранилища на основе сериализации Java.
 */
public class BinaryFigureStorage implements FigureStorage {

    @Override
    public List<Figure> read(Path path)
            throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(path)))) {
            Object obj = in.readObject();
            if (!(obj instanceof List<?> rawList)) {
                throw new IOException("Ожидался список фигур, получено: "
                        + obj.getClass().getName());
            }
            List<Figure> figures = new ArrayList<>();
            for (Object element : rawList) {
                if (!(element instanceof Figure figure)) {
                    throw new IOException("Элемент списка не является Figure: "
                            + (element == null ? "null" : element.getClass().getName()));
                }
                figures.add(figure);
            }
            return figures;
        }
    }

    @Override
    public void write(Path path, List<Figure> figures) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(path)))) {
            out.writeObject(new ArrayList<>(figures));
        }
    }
}