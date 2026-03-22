package se.yrgo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// En entitets-klass som motsvarar tabellen (Author) i databasen

@Entity
public class Author {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String nationality;

    @OneToMany
    private List<Book> books = new ArrayList<>();

    public Author() {}

    public Author(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public String getName() {
        return name;
    }
}
