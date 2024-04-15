package org.example.cuisine;

import java.util.List;

public class Recette {

    private String nomRecette;
    private List<Ingredient> ingredients;
    private AppareilCuisson modeCuisson;
    private List<EtapeRealisation> etapeRealisations;
    boolean exquis;

    public Recette(String nomRecette, List<Ingredient> ingredients, AppareilCuisson modeCuisson, List<EtapeRealisation> etapeRealisations, boolean exquis) {
        this.ingredients = ingredients;

        this.nomRecette = nomRecette;

        this.modeCuisson = modeCuisson;

        this.etapeRealisations = etapeRealisations;

        this.exquis = exquis;
     }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getNomRecette() {
        return nomRecette;
    }

    public AppareilCuisson getModeCuisson() {
        return modeCuisson;
    }

    public List<EtapeRealisation> getEtapeRealisations() {
        return etapeRealisations;
    }

    public boolean isExquis() {
        return exquis;
    }
}
