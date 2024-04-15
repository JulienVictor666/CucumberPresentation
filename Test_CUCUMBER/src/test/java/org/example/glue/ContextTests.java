package org.example.glue;

import org.example.cuisine.AppareilCuisson;
import org.example.cuisine.EtapeRealisation;
import org.example.cuisine.Ingredient;
import org.example.cuisine.Recette;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextTests {
    public String nomRecetteARealiser;
    public List<Ingredient> ingredients = new ArrayList<>();
    public AppareilCuisson appareilCuisson;
    public List<EtapeRealisation> etapeRealisations = new ArrayList<>();

    public void initContext(){
        nomRecetteARealiser = null;
        ingredients = new ArrayList<>();
        appareilCuisson = null;
        etapeRealisations = new ArrayList<>();
    }
}
