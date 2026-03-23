package se.yrgo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Entitetsklass som mappas till tabellen "Reader" i databasen

@Entity
public class Reader {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @ManyToMany
    private List<Book> books = new ArrayList<>();

    public Reader() {}

    public Reader(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public String getName() {
        return name;
    }
}
