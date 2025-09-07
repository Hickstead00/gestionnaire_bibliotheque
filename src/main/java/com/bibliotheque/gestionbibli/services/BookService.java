package com.bibliotheque.gestionbibli.services;

import com.bibliotheque.gestionbibli.entity.Book;
import com.bibliotheque.gestionbibli.entity.ReadingStatus;
import com.bibliotheque.gestionbibli.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    /**
     * Récupère tout les livres de la bibliothèque
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Ajoute un nouveau livre avec validation
     */
    public Book addBook(Book book) {
        //1. ISBN obligatoire
        if (book.getIsbn() == null || book.getIsbn().isEmpty()) {
            throw new RuntimeException("L'ISBN est obligatoire");
        }

        //2. Validation du format ISBN 13 chiffres
        String isbn = book.getIsbn().trim();
        if (!isbn.matches("\\d{13}")){
            throw new RuntimeException("L'ISBN doit contenir exactement 13 chiffres");
        }

        //3. ISBN unique
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new RuntimeException("Un livre avec cet ISBN est déjà présent : " + book.getTitle());
        }

        //4. Stockage de l'ISBN nettoyé
        book.setIsbn(isbn);

        //5. Valeurs par défaut
        if (book.getDateAdded() == null) {
            book.setDateAdded(LocalDateTime.now());
        }
        if (book.getReadingStatus() == null){
            book.setReadingStatus(ReadingStatus.TO_READ);
        }

        return bookRepository.save(book);
    }

    /**
     * Récupère un livre via son isbn
     * @param isbn : L'ISBN unique du livre
     */
    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    /**
     * Permet de supprimer un livre via son ISBN
     * @param isbn : L'ISBN unique du livre
     */
    public boolean deleteBookByIsbn(String isbn) {
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);

        if (existingBook.isEmpty()){
            throw new RuntimeException("Livre non trouvé avec l'ISBN : " + isbn);
        }

        bookRepository.delete(existingBook.get());
        return true;
    }

    /**
     * Permet d'update le statut de lecture du livre via les 3 enums
     * @param isbn : ISBN unique du livre
     * @param readingStatus : Enum disponible (TO_READ,READING,READ)
     */
    public Book updateReadingStatus(String isbn, ReadingStatus readingStatus) {
        Optional<Book> existingBook = bookRepository.findByIsbn(isbn);

        if (existingBook.isEmpty()){
            throw new RuntimeException("Livre non trouvé avec l'ISBN : " + isbn);
        }

        Book book = existingBook.get();
        book.setReadingStatus(readingStatus);

        return bookRepository.save(book);
    }
}




