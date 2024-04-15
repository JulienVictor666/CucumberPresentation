package org.example.glue;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.ParameterType;
import io.cucumber.java.fr.Alors;
import io.cucumber.java.fr.Et;
import io.cucumber.java.fr.Etantdonnéque;
import io.cucumber.java.fr.Quand;
import org.example.cuisine.AppareilCuisson;
import org.example.cuisine.EtapeRealisation;
import org.example.cuisine.Ingredient;
import org.example.cuisine.Recette;

import java.util.*;

import static org.junit.Assert.assertTrue;

public class StepRecettes {

    ContextTests context = new ContextTests();

    public static Map<String, Recette> recetteConnu= new HashMap<>();

    @BeforeAll
    public static void InitGeneral() {

        List<Ingredient> ingredients = new ArrayList<>();
        List<EtapeRealisation> etapeRealisations = new ArrayList<>();

        ingredients.add(Ingredient.FARINE);
        ingredients.add(Ingredient.BEURRE);
        ingredients.add(Ingredient.OEUFS);
        ingredients.add(Ingredient.CAROTTES);
        ingredients.add(Ingredient.CHAMPIGNONS);

        etapeRealisations.add(EtapeRealisation.MELANGER);
        etapeRealisations.add(EtapeRealisation.CUIRE);
        etapeRealisations.add(EtapeRealisation.COUPER);
        etapeRealisations.add(EtapeRealisation.MELANGER);
        etapeRealisations.add(EtapeRealisation.CUIRE);

        Recette recette = new Recette(
                "tarte aux légumes",
                ingredients,
                AppareilCuisson.FOUR,
                etapeRealisations,
                true);

        recetteConnu.put(recette.getNomRecette(), recette);

        ingredients = new ArrayList<>();
        etapeRealisations = new ArrayList<>();

        ingredients.add(Ingredient.FARINE);
        ingredients.add(Ingredient.BEURRE);
        ingredients.add(Ingredient.OEUFS);
        ingredients.add(Ingredient.POMME);

        etapeRealisations.add(EtapeRealisation.MELANGER);
        etapeRealisations.add(EtapeRealisation.CUIRE);
        etapeRealisations.add(EtapeRealisation.COUPER);
        etapeRealisations.add(EtapeRealisation.MELANGER);
        etapeRealisations.add(EtapeRealisation.CUIRE);

        recette = new Recette(
                "tarte aux pommes",
                ingredients,
                AppareilCuisson.FOUR,
                etapeRealisations,
                true);

        recetteConnu.put(recette.getNomRecette(), recette);

        ingredients = new ArrayList<>();
        etapeRealisations = new ArrayList<>();

        ingredients.add(Ingredient.FARINE);
        ingredients.add(Ingredient.SUCRE);
        ingredients.add(Ingredient.BEURRE);
        ingredients.add(Ingredient.OEUFS);
        ingredients.add(Ingredient.LEVURE);
        ingredients.add(Ingredient.LAIT);
        ingredients.add(Ingredient.CHOCOLAT);


        etapeRealisations.add(EtapeRealisation.MELANGER);
        etapeRealisations.add(EtapeRealisation.ATTENDRE);
        etapeRealisations.add(EtapeRealisation.CUIRE);

        recette = new Recette(
                "brioche",
                ingredients,
                AppareilCuisson.FOUR,
                etapeRealisations,
                true);

        recetteConnu.put(recette.getNomRecette(), recette);

        ingredients = new ArrayList<>();
        etapeRealisations = new ArrayList<>();

        ingredients.add(Ingredient.SACHET_SURGELE);

        etapeRealisations.add(EtapeRealisation.CUIRE);

        recette = new Recette(
                "plat surgelé",
                ingredients,
                AppareilCuisson.MICRO_ONDE,
                etapeRealisations,
                false);

        recetteConnu.put(recette.getNomRecette(), recette);
    }

    @Before //Attention ne pas utiliser le before junit mais bien celui cucumber
    // Cette annotation lance cette méthode avant le lancement de chaque scénario
    public void init() {
        context.initContext();
    }

    @Etantdonnéque("J'ai les ingrédient suivant :")
    public void jAiLesIngredientSuivant(DataTable table) {
        // Cette étape utilise attend d'avoir un dataTable en entré. S'est un objet particulier qui peut s'utilise sans le convertir
        // mais il est très souvent plus simple de le convertir en liste ou map ou list de list ou map de map
        List<String> rowIngredients = table.asList(String.class);

        for(String row: rowIngredients) {
            context.ingredients.add(Ingredient.valueOf(row));
        }

    }

    @Etantdonnéque("J'ai appris les recettes suivantes :")
    public void jAiApprisLesRecettesSuivantes(Map<String, Map<String, String>> table) {
        // On récupère les lignes en premiers
        for (Map.Entry<String, Map<String, String>> entry : table.entrySet()) {
            String nomRecette = entry.getKey();
            String stringIngredients = entry.getValue().get("Ingredients");
            String stringModeCuisson = entry.getValue().get("ModeCuisson");
            String stringEtapes = entry.getValue().get("Etapes");
            String stringExquis = entry.getValue().get("Exquis");

            List<String> ingredientsString = new ArrayList<>(Arrays.asList(stringIngredients.split(",")));
            List<Ingredient> ingredients = new ArrayList<>();
            for(String ingredientString : ingredientsString) {
                ingredients.add(Ingredient.valueOf(ingredientString));
            }

            List<String> etapesString = new ArrayList<>(Arrays.asList(stringEtapes.split(",")));
            List<EtapeRealisation> etapes = new ArrayList<>();
            for(String etapeString : etapesString) {
                etapes.add(EtapeRealisation.valueOf(etapeString));
            }

            Recette recette = new Recette(nomRecette, ingredients, AppareilCuisson.valueOf(stringModeCuisson), etapes, stringExquis.equalsIgnoreCase("true"));

            recetteConnu.put(recette.getNomRecette(), recette);
        }
    }

    // Les parentèse permettent de rentre obtionnel un ou plusieurs caractère
    @Et("j'ai un(e) {appareil}")
    public void jAiUnePLAQUE_DE_CUISSON(AppareilCuisson appareilCuisson) {
        context.appareilCuisson = appareilCuisson;
    }

    @Et("je veux faire la recette {string}")
    public void jeVeuxFaireLaRecette(String recette) throws Exception {
        if (recetteConnu.containsKey(recette)) {
            context.nomRecetteARealiser = recette;
        }
        else {
            throw new Exception("cette recette n'est pas connu");
        }
    }

    @Quand("Je réalise l'action : {etape}")
    public void jeRealiseAction(EtapeRealisation etape) {
        context.etapeRealisations.add(etape);
    }

    @Alors("Je réussi la recette")
    public void jeReussiLaRecette() {
        verifReussiteRecette();
    }

    //Paramètre spécifique
    @ParameterType("FOUR|MICRO_ONDE|PLAQUE_DE_CUISSON")  // regexp
    public AppareilCuisson appareil(String appareil){
        return AppareilCuisson.valueOf(appareil);
    }

    @ParameterType("COUPER|MELANGER|ATTENDRE|CUIRE")  // regexp
    public EtapeRealisation etape(String etape){
        return EtapeRealisation.valueOf(etape);
    }

    public void verifReussiteRecette() {

        Recette recette = recetteConnu.get(context.nomRecetteARealiser);
        List<Ingredient> ingredientsRecette = recette.getIngredients();

        boolean ingredientOK = true;

        for(Ingredient ingredient : ingredientsRecette) {
            if (!context.ingredients.contains(ingredient)) {
                ingredientOK = false;
                break;
            }
        }

        boolean materielOK = context.appareilCuisson == recette.getModeCuisson();

        boolean etapeOK = true;

        List<EtapeRealisation> etapesRecette = recette.getEtapeRealisations();

        if (etapesRecette.size() == context.etapeRealisations.size()) {
            for(int i = 0; i < etapesRecette.size(); i++) {
                if (context.etapeRealisations.get(i) != etapesRecette.get(i)) {
                    etapeOK = false;
                    break;
                }
            }
        }

        assertTrue("La recette est raté", ingredientOK && materielOK && etapeOK);
    }

}
