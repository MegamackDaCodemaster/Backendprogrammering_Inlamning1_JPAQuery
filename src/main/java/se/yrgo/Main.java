package se.yrgo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import se.yrgo.entity.*;
import se.yrgo.util.JpaUtil;

public class Main {

    public static void main(String[] args) {

        // Hämtar EntityManager för att kunna spara objekt, koppla ihop objekt
        // enligt relationsmallen och köra frågor mot databasen

        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();

        // _________________________
        // Uppgift 1: Skapa, koppla ihop och spara data
        // -------------------------

        Author a1 = new Author("Tolkien", "UK");
        Author a2 = new Author("Astrid Lindgren", "Sweden");
        Author a3 = new Author("Murakami", "Japan");

        Book b1 = new Book("The Hobbit", "Fantasy", 1937);
        Book b2 = new Book("Pippi Långstrump", "Children", 1945);
        Book b3 = new Book("Kafka on the Shore", "Magical Realism", 2002);
        Book b4 = new Book("The Silmarillion", "Fantasy", 1977);
        Book b5 = new Book("Ronja Rövardotter", "Children", 1981);

        a1.addBook(b1);
        a1.addBook(b4);
        a2.addBook(b2);
        a2.addBook(b5);
        a3.addBook(b3);

        em.persist(a1);
        em.persist(a2);
        em.persist(a3);

        em.persist(b1);
        em.persist(b2);
        em.persist(b3);
        em.persist(b4);
        em.persist(b5);

        Reader r1 = new Reader("Anna", "anna@mail.com");
        Reader r2 = new Reader("Björn", "bjorn@mail.com");
        Reader r3 = new Reader("Clara", "clara@mail.com");

        r1.addBook(b1);
        r1.addBook(b2);

        r2.addBook(b3);

        r3.addBook(b1);
        r3.addBook(b5);

        em.persist(r1);
        em.persist(r2);
        em.persist(r3);

        // -------------------------
        // Uppgift 2: Skapa en fråga som listar böcker av författaren "Tolkien"
        // -------------------------

        System.out.println("\nUppgift 2:");
        TypedQuery<Author> q2 = em.createQuery(
                "SELECT a FROM Author a WHERE a.name = :name", Author.class);
        q2.setParameter("name", "Tolkien");

        Author resultAuthor = q2.getSingleResult();
        resultAuthor.getBooks().forEach(b -> System.out.println(b.getTitle()));

        // -------------------------
        // Uppgift 3: Skapa en fråga som listar personer som har läst boken "The Hobbit"
        // -------------------------

        System.out.println("\nUppgift 3:");
        TypedQuery<Reader> q3 = em.createQuery(
                "SELECT r FROM Reader r WHERE :book MEMBER OF r.books", Reader.class);
        q3.setParameter("book", b1);

        q3.getResultList().forEach(r -> System.out.println(r.getName()));
    }
}
