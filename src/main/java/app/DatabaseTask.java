package app;

import app.model.Book;
import app.model.Visitor;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.List;

public class DatabaseTask {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";

    public static void execute() {
        try (Connection conn = DriverManager.getConnection(URL, "sa", "")) {
            System.out.println("Успешное подключение к базе данных H2.");

            initMusicTable(conn);

            System.out.println("\n=== Задание 1: Все композиции ===");
            printAllMusic(conn);

            System.out.println("\n=== Задание 2: Без 'm' и 't' ===");
            printFilteredMusic(conn);

            System.out.println("\n=== Задание 3: Добавление 'My Favorite Song' ===");
            addMusic(conn, "My Favorite Song");

            System.out.println("\n=== Задание 4: Загрузка данных из JSON ===");
            loadDataFromJson(conn, "boks.json");

            System.out.println("\n=== Задание 5: Сортировка книг по году ===");
            printBooksSortedByYear(conn);

            System.out.println("\n=== Задание 6: Книги до 2000 года ===");
            printBooksBefore2000(conn);

            System.out.println("\n=== Задание 7: Добавление себя ===");
            addMeAndMyBooks(conn);

            System.out.println("\n=== Задание 8: Удаление таблиц ===");
            dropTables(conn);

        } catch (SQLException e) {
            System.err.println("Ошибка БД: " + e.getMessage());
        }
    }

    private static void initMusicTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            String script;
            try (var is = DatabaseTask.class.getClassLoader().getResourceAsStream("music-create.sql")) {
                if (is == null) throw new RuntimeException("Файл music-create.sql не найден в ресурсах!");
                script = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                throw new RuntimeException("Ошибка чтения SQL-скрипта", e);
            }

            for (String sql : script.split(";")) {
                if (!sql.trim().isEmpty()) {
                    stmt.execute(sql);
                }
            }
        }
    }

    private static void printAllMusic(Connection conn) throws SQLException {
        String sql = "SELECT * FROM study.music";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("id") + ": " + rs.getString("name"));
            }
        }
    }

    private static void printFilteredMusic(Connection conn) throws SQLException {
        String sql = "SELECT * FROM study.music WHERE LOWER(name) NOT LIKE '%m%' AND LOWER(name) NOT LIKE '%t%'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }
        }
    }

    private static void addMusic(Connection conn, String title) throws SQLException {
        String sql = "INSERT INTO study.music (name) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Добавлена композиция: " + title);
        }
    }

    private static void loadDataFromJson(Connection conn, String jsonFileName) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS visitors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "surname VARCHAR(100), " +
                    "phone VARCHAR(20) UNIQUE, " +
                    "subscribed BOOLEAN)");

            stmt.execute("CREATE TABLE IF NOT EXISTS books (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(255), " +
                    "author VARCHAR(255), " +
                    "publishing_year INT, " +
                    "isbn VARCHAR(20) UNIQUE, " +
                    "publisher VARCHAR(255))");

            stmt.execute("CREATE TABLE IF NOT EXISTS visitor_books (" +
                    "visitor_id INT, " +
                    "book_id INT, " +
                    "PRIMARY KEY (visitor_id, book_id), " +
                    "FOREIGN KEY (visitor_id) REFERENCES visitors(id), " +
                    "FOREIGN KEY (book_id) REFERENCES books(id))");
        }

        try {
            java.nio.file.Path path = java.nio.file.Path.of("src", "main", "resources", jsonFileName).toAbsolutePath();

            if (!java.nio.file.Files.exists(path)) {
                path = java.nio.file.Path.of("data", jsonFileName).toAbsolutePath();
            }

            String json = java.nio.file.Files.readString(path, StandardCharsets.UTF_8);

            Type listType = new TypeToken<List<Visitor>>() {}.getType();
            List<Visitor> visitors = new Gson().fromJson(json, listType);

            for (Visitor v : visitors) {
                int visitorId = insertVisitor(conn, v);
                if (visitorId == -1) continue;

                for (Book b : v.getFavoriteBooks()) {
                    int bookId = insertBook(conn, b);
                    if (bookId != -1) {
                        insertVisitorBook(conn, visitorId, bookId);
                    }
                }
            }
            System.out.println("Данные из JSON загружены.");
        } catch (IOException e) {
            System.err.println("Ошибка чтения JSON: " + e.getMessage());
        }
    }

    private static int insertVisitor(Connection conn, Visitor v) throws SQLException {
        String sql = "INSERT INTO visitors (name, surname, phone, subscribed) " +
                "SELECT ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM visitors WHERE phone = ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getName());
            ps.setString(2, v.getSurname());
            ps.setString(3, v.getPhone());
            ps.setBoolean(4, v.isSubscribed());
            ps.setString(5, v.getPhone());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    private static int insertBook(Connection conn, Book b) throws SQLException {
        String sql = "INSERT INTO books (name, author, publishing_year, isbn, publisher) " +
                "SELECT ?, ?, ?, ?, ? WHERE NOT EXISTS (SELECT 1 FROM books WHERE isbn = ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, b.getName());
            ps.setString(2, b.getAuthor());
            ps.setInt(3, b.getPublishingYear());
            ps.setString(4, b.getIsbn());
            ps.setString(5, b.getPublisher());
            ps.setString(6, b.getIsbn());
            int affected = ps.executeUpdate();
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        }
        // Если книга уже есть, возвращаем её ID
        try (PreparedStatement ps = conn.prepareStatement("SELECT id FROM books WHERE isbn = ?")) {
            ps.setString(1, b.getIsbn());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt("id");
            }
        }
        return -1;
    }

    private static void insertVisitorBook(Connection conn, int visitorId, int bookId) throws SQLException {
        String sql = "INSERT INTO visitor_books (visitor_id, book_id) " +
                "SELECT ?, ? WHERE NOT EXISTS (SELECT 1 FROM visitor_books WHERE visitor_id = ? AND book_id = ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, visitorId);
            ps.setInt(2, bookId);
            ps.setInt(3, visitorId);
            ps.setInt(4, bookId);
            ps.executeUpdate();
        }
    }

    private static void printBooksSortedByYear(Connection conn) throws SQLException {
        String sql = "SELECT name, author, publishing_year FROM books ORDER BY publishing_year ASC";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getInt("publishing_year") + " - " +
                        rs.getString("name") + " (" + rs.getString("author") + ")");
            }
        }
    }

    private static void printBooksBefore2000(Connection conn) throws SQLException {
        String sql = "SELECT name, publishing_year FROM books WHERE publishing_year < 2000 ORDER BY publishing_year";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(rs.getString("name") + " (" + rs.getInt("publishing_year") + ")");
            }
        }
    }

    private static void addMeAndMyBooks(Connection conn) throws SQLException {
        String myName = "Ivan";
        String mySurname = "Ivanov";
        String myPhone = "+79990000000";
        String myIsbn = "978-3-16-148410-0";

        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO visitors (name, surname, phone, subscribed) VALUES (?, ?, ?, true)",
                Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, myName);
            ps.setString(2, mySurname);
            ps.setString(3, myPhone);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int myId = rs.getInt(1);
                    try (PreparedStatement psBook = conn.prepareStatement(
                            "INSERT INTO books (name, author, publishing_year, isbn, publisher) VALUES (?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS)) {
                        psBook.setString(1, "Моя любимая книга");
                        psBook.setString(2, "Мой любимый автор");
                        psBook.setInt(3, 2020);
                        psBook.setString(4, myIsbn);
                        psBook.setString(5, "Мое издательство");
                        psBook.executeUpdate();
                        try (ResultSet rsBook = psBook.getGeneratedKeys()) {
                            if (rsBook.next()) {
                                int bookId = rsBook.getInt(1);
                                insertVisitorBook(conn, myId, bookId);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Данные добавлены. Проверка:");
        String sql = "SELECT v.name, v.surname, b.name AS book_name FROM visitors v " +
                "JOIN visitor_books vb ON v.id = vb.visitor_id " +
                "JOIN books b ON vb.book_id = b.id WHERE v.phone = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, myPhone);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("name") + " " + rs.getString("surname") +
                            " любит книгу: " + rs.getString("book_name"));
                }
            }
        }
    }

    private static void dropTables(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS visitor_books");
            stmt.execute("DROP TABLE IF EXISTS books");
            stmt.execute("DROP TABLE IF EXISTS visitors");
            System.out.println("Таблицы visitors, books и visitor_books удалены.");
        }
    }
}