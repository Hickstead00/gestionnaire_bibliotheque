package com.bibliotheque.gestionbibli.entity;

/**
 * Enum pour représenter les différents statuts de lecture d'un livre
 */

public enum ReadingStatus {
    TO_READ("A lire"),
    READING("En cours de lecture"),
    READ("Lu");

    /** Label français pour l'affichage personnel
     */
    private final String displayName;

    /**
     * Consutructeur de l'enum
     * @param displayName
     */
    ReadingStatus(String displayName){
        this.displayName = displayName;
    }

    public String getDisplayName(){
        return displayName;
    }

    /**
     * Methode pour convertir un string en ReadingStatus et éviter les erreurs
     */
    public static ReadingStatus fromString(String status){
        if (status == null){
            return TO_READ;
        }

        try {
            return ReadingStatus.valueOf(status.toUpperCase());
        }
        catch (IllegalArgumentException e){
            return TO_READ;
        }
    }

}
