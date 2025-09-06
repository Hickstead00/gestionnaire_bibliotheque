package com.bibliotheque.gestionbibli;

import com.bibliotheque.gestionbibli.entity.Book;
import com.bibliotheque.gestionbibli.entity.ReadingStatus;
import com.bibliotheque.gestionbibli.repository.BookRepository;
import com.bibliotheque.gestionbibli.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class GestionbibliApplication implements CommandLineRunner {

    /**
     * @Aytowired = Spring donne une instance de BookService
     */
    @Autowired
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(GestionbibliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n=== TESTS BookService");

        // Test 1: Ajout livre valide
        try {
            Book book1 = new Book();
            book1.setIsbn("9782123456789");
            book1.setTitle("Test Book 1");
            book1.setAuthor("AAA");

            Book savedBook = bookService.addBook(book1);
            System.out.println("Livre ajouté: " + savedBook.getTitle() +
                    " (Status: " + savedBook.getReadingStatus() + ")");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
        }

        // Test 2: ISBN invalide (pas 13 chiffres)
        try {
            Book book2 = new Book();
            book2.setIsbn("123"); // Trop court !
            book2.setTitle("Test Book 2");

            bookService.addBook(book2);
        } catch (Exception e) {
            System.out.println("Validation ISBN : " + e.getMessage());
        }

        // Test 3: ISBN en doublon
        try {
            Book book3 = new Book();
            book3.setIsbn("9782123456789");
            book3.setTitle("Autre livre");

            bookService.addBook(book3);
        } catch (Exception e) {
            System.out.println("Validation doublon : " + e.getMessage());
        }

        // Test 4: Récupération de tous les livres
        System.out.println("Total livres: " + bookService.getAllBooks().size());
    }
}

