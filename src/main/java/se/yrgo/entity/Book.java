package se.yrgo.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// En klass som skapar bok-objekt, mappas till tabellen "Book" i databasen

@Entity
@NamedQuery(
        name = "Book.findByGenre",
        query = "SELECT b FROM Book b WHERE b.genre = :genre"
)
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String genre;
    private int publicationYear;

    @ManyToMany(mappedBy = "books")
    private List<Reader> readers = new ArrayList<>();

    public Book() {}

    public Book(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.publicationYear = year;
    }

    public String getTitle() {
        return title;
    }
}
