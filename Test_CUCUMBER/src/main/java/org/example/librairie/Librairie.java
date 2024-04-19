package org.example.librairie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Librairie {

    Map<String, Livre> livres = new HashMap<>();

    public void ajoutLivre(String titre, double prix, String auteur, String editeur, GenreLivre genre) {
        livres.put(titre, new Livre(titre, prix, auteur, editeur, genre));
    }

    public Livre rechercheLivreTitre(String titre) {

        return livres.get(titre);
    }

    public List<Livre> rechercheLivresAuteur(String auteur) {

        List<Livre> result = new ArrayList<>();

        for(Map.Entry<String, Livre> entry : livres.entrySet()) {
            if (entry.getValue().getAuteur().equalsIgnoreCase(auteur)) {
                result.add(entry.getValue());
            }
        }

        return result;
    }

    public List<Livre> recherchesLivreGenre(GenreLivre genre) {

        List<Livre> result = new ArrayList<>();

        for(Map.Entry<String, Livre> entry : livres.entrySet()) {
            if (entry.getValue().getGenre().equals(genre)) {
                result.add(entry.getValue());
            }
        }

        return result;
    }

    public Livre rechercheLivreAuteurMoinsChere (String auteur) {

        Livre result = null;

        for(Map.Entry<String, Livre> entry : livres.entrySet()) {
            if (entry.getValue().getAuteur().equalsIgnoreCase(auteur)) {
                if (result == null || result.getPrix() > entry.getValue().getPrix()) {
                    result = entry.getValue();
                }
            }
        }

        return result;
    }

    public Livre rechercheLivreGenreMoinsChere(GenreLivre genre) {

        Livre result = null;

        for(Map.Entry<String, Livre> entry : livres.entrySet()) {
            if (entry.getValue().getGenre().equals(genre)) {
                if (result == null || result.getPrix() > entry.getValue().getPrix()) {
                    result = entry.getValue();
                }
            }
        }

        return result;
    }
}
