package app;

import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.stream.Stream;

public class FileSystemTask {

    private static final String SURNAME = "Dabagyan";
    private static final String NAME = "Christina";

    public static void execute() {
        System.out.println("\n=== ЗАДАНИЕ 2: Работа с файловой системой ===");
        Path baseDir = Paths.get(SURNAME);

        try {
            // 1. Создаем директорию <surname>
            Files.createDirectories(baseDir);
            System.out.println("Создана директория: " + baseDir.toAbsolutePath());

            // 2. Создаем файл <name> внутри <surname>
            Path originalFile = baseDir.resolve(NAME + ".txt");
            if (!Files.exists(originalFile)) {
                Files.createFile(originalFile);
            }
            System.out.println("Создан файл: " + originalFile);

            // 3. Создаем вложенные директории dir1/dir2/dir3 и копируем туда файл
            Path nestedDirs = baseDir.resolve("dir1/dir2/dir3");
            Files.createDirectories(nestedDirs);

            Path copiedFile = nestedDirs.resolve(NAME + ".txt");
            Files.copy(originalFile, copiedFile, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Файл скопирован в: " + copiedFile);

            // 4. Внутри dir1 создаем file1
            Path file1 = baseDir.resolve("dir1/file1.txt");
            if (!Files.exists(file1)) Files.createFile(file1);

            // 5. Внутри dir2 создаем file2
            Path file2 = baseDir.resolve("dir1/dir2/file2.txt");
            if (!Files.exists(file2)) Files.createFile(file2);

            // 6. Рекурсивный обход с метками D: и F:
            System.out.println("\nСодержимое директории " + SURNAME + ":");
            try (Stream<Path> paths = Files.walk(baseDir)) {
                paths.forEach(path -> {
                    String prefix = Files.isDirectory(path) ? "D: " : "F: ";
                    // Выводим путь относительно корневой папки, чтобы не было длинных путей
                    System.out.println(prefix + baseDir.relativize(path));
                });
            }

            // 7. Удаление директории dir1 со всем содержимым
            Path dir1ToDelete = baseDir.resolve("dir1");
            if (Files.exists(dir1ToDelete)) {
                // Для удаления папки с файлами нужно сначала удалить файлы (в обратном порядке)
                try (Stream<Path> pathsToDelete = Files.walk(dir1ToDelete)) {
                    pathsToDelete.sorted(Comparator.reverseOrder())
                            .forEach(p -> {
                                try {
                                    Files.delete(p);
                                } catch (IOException e) {
                                    System.err.println("Не удалось удалить: " + p);
                                }
                            });
                }
                System.out.println("\nДиректория dir1 успешно удалена.");
            }

        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }
}