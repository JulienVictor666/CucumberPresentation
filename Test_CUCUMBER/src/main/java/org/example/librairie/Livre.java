package org.example.librairie;

import java.util.Objects;

public class Livre {
    private String titre;
    private double prix;
    private String auteur;
    private String editeur;
    private GenreLivre genre;

    public Livre(String titre, double prix, String auteur, String editeur, GenreLivre genre) {
        this.titre = titre;
        this.prix = prix;
        this.auteur = auteur;
        this.editeur = editeur;
        this.genre = genre;
    }

    public String getTitre() {
        return titre;
    }

    public double getPrix() {
        return prix;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public GenreLivre getGenre() {
        return genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return prix == livre.prix && titre.equals(livre.titre) && auteur.equals(livre.auteur) && editeur.equals(livre.editeur) && genre == livre.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(titre, prix, auteur, editeur, genre);
    }
}
