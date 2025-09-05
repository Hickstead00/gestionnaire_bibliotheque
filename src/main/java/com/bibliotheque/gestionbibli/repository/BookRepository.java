package com.bibliotheque.gestionbibli.repository;

import com.bibliotheque.gestionbibli.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    //Méthode pour chercher par ISBN
    Optional<Book> findByIsbn(String isbn);
}
