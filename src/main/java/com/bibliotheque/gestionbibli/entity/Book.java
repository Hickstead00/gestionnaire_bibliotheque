package com.bibliotheque.gestionbibli.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Entité Book qui représent un livre dans la bibliothèque
 *
 * @Entity : Indique à JPA que cette classe correspond à une table en base
 * @Table : Spécifie le nom de la table en base
 */

@Entity
@Table(name="books")

public class Book {

    /**
     * Clé primaire de la table
     * @Id : Clé primaire
     * @GeneratedValue : La base génère automatiquement les ID's
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ISBN du livre
     * @Column : Personnalise la colonne (unicité de l'ISBN et longueur)
     */
    @Column(unique = true, length = 13)
    private String isbn;

    /**
     * Informations principales du livre
     * nullable = false : Premier champ obligatoire, reste optionnel
     */
    @Column(nullable = false)
    private String title;

    private String author;
    private String publisher;
    private String genre;
    private Integer publicationYear;
    private Integer pagesCount;

    /**
     * Description longue du livre
     * @Lob : Large Object - texte long ici
     */
    @Lob
    private String description;

    private String coverImageUrl;

    /**
     * Statut de lecture - Enum pour contrôler les valeurs
     * @Enumerated : Indique comment stocker l'enum en base
     * String : Stocke le nom ("A_LRE" par défaut au lieu de 0)
     */
    @Enumerated(EnumType.STRING)
    private ReadingStatus readingStatus = ReadingStatus.TO_READ;

    private LocalDateTime dateAdded;

    /**
     * Notes personnelles sur le livre
     */
    @Lob
    private String notes;

    /**
     * Constructeur par défaut
     */
    public Book() {
        this.dateAdded = LocalDateTime.now();
    }

    /**
     * Constructeur avec les champs essentiels
     */
    public Book(String isbn,String title, String author){
        this();
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Integer getPagesCount() {
        return pagesCount;
    }

    public void setPagesCount(Integer pagesCount) {
        this.pagesCount = pagesCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoverImageUrl() {
        return coverImageUrl;
    }

    public void setCoverImageUrl(String coverImageUrl) {
        this.coverImageUrl = coverImageUrl;
    }

    public ReadingStatus getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(ReadingStatus readingStatus) {
        this.readingStatus = readingStatus;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * toString()
     */
    @Override
    public String toString() {
        return "Book [id=" + id + ", isbn=" + isbn + ", title=" + title + ", author=" + author + "]";
    }
}
