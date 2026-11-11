package app.model;

import java.util.List;

public class Visitor {
    private String name;
    private String surname;
    private String phone;
    private List<Book> favoriteBooks;
    private boolean subscribed;

    public Visitor() {}

    public Visitor(String name, String surname, String phone, List<Book> favoriteBooks, boolean subscribed) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.favoriteBooks = favoriteBooks;
        this.subscribed = subscribed;
    }

    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getPhone() { return phone; }
    public List<Book> getFavoriteBooks() { return favoriteBooks; }
    public boolean isSubscribed() { return subscribed; }
}