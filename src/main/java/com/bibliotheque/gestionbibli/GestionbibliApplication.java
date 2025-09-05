package com.bibliotheque.gestionbibli;

import com.bibliotheque.gestionbibli.entity.Book;
import com.bibliotheque.gestionbibli.entity.ReadingStatus;
import com.bibliotheque.gestionbibli.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class GestionbibliApplication implements CommandLineRunner {

    /**
     * @Aytowired = Spring donne une instance de BookRepository
     */
    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(GestionbibliApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("DEBUT TEST");

        //Test 1 créer et sauvegarder un livre
        System.out.println("\n 1. Création d'un livre");
        Book livre1 = new Book("9782070360024","Le petit prince","Antoine de St exupery");
        livre1.setGenre("Conte");
        livre1.setPublicationYear(1943);

        Book livreSauvegarde = bookRepository.save(livre1);
        System.out.println("Livre sauvegardé :" + livreSauvegarde);

        //Test 2 récupérer tous les livres
        System.out.println("\n 2. Liste de tout les livres");
        List<Book> toutLeslivres = bookRepository.findAll();
        System.out.println("Nombre de livre : " + toutLeslivres.size() );
        for (Book livre : toutLeslivres) {
            System.out.println(" - " + livre.getTitle() + " par " + livre.getAuthor() );
        }

        //Test 3 chercher un livre par Isbn
        System.out.println("\n 3. Recherche par ISBN");
        Optional<Book> livreTrouve = bookRepository.findByIsbn("9782070360024");
        if (livreTrouve.isPresent()) {
            System.out.println(" - " + livreTrouve.get().getTitle());
        } else {
            System.out.println("Livre non trouvé");
        }

        //Test 4 ajout d'un deuxième livre
        System.out.println("\n 4. Ajout d'un deuxième livre");
        Book livre2 = new Book("1234567891234","Titre","Moi");
        livre2.setReadingStatus(ReadingStatus.READING);
        bookRepository.save(livre2);

        //Test 5 compter les livres
        System.out.println("\n 5. Compter les livres");
        long nbLivre = bookRepository.count();
        System.out.println("Total des livres : " + nbLivre);

        System.out.println("\n TESTS TERMINES");
    }

}
