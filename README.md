# Presentation de CUCUMBER et GHERKIN
Petit projet pour présenter les fonctionnalités de CUCUMBER

On a un petit programme qui simule une cuisine. Le cuisinié doit connaitre la recette, avoir les ingrédients, avoir le matériel de cuisson et réaliser les actions nécessaires pour faire la recette. On vérifie à la fin que tout était fait selon la recette.

## Le langage GHERKIN
On va écrire cela en langage GHERKIN. Ce langage permet de décrire le fonctionnement d'un système de manière séquentiel. On va donc avoir des étapes qui décrive l'état initial du système, puis des étapes qui décrivent les actions que l'on réalise et enfin des étapes qui donnent le résultat que l'on attend.

Un mot clé en début d'étape définie son type. On aura donc des étapes du type suivant :

* `Given` - conditions initiales
* `When` - actions à réaliser
* `Then` - résultat attendu
* `And` - Reprend le mot clé au-dessus de lui

Ces mots clé seront suivi d'une phrase décrivant l'étape en elle-même.

```gherkin
Given je veux faire la recette "tarte aux légumes" et j'ai les ingrédients
And J'ai le matériel nécessaire
When Je réalise les actions nécessaire à la recette
Then Je réussi la recette
```

Le code GHERKIN est écrit dans des fichiers avec l'extention ".feature". Il est possible de positioner des options en début de fichier pour indiquer par exemple la langue qui sera utilisée.
Dans ce projet, nous avons défini la langue des fichiers ".feature" à Français avec la ligne

`#language: fr`

Dans ce cas les mots clé change

* `Etant donné que` - conditions initiales
* `Quand` - actions à réaliser
* `Alors` - résultat attendu
* `Et` - Reprend le mot clé au-dessus de lui

Le nom de la recette, les ingrédients et le matériel sont les conditions de départ. Ensuite, on doit réaliser les étapes de la recette et enfin vérifier si le plat et réussi

```gherkin
# language: fr
  
Etant donné que je veux faire la recette "tarte aux légumes" et j'ai les ingrédients
Et J'ai le matériel nécessaire
Quand Je réalise les actions nécessaire à la recette
Alors Je réussi la recette
```

D'autres mots clé sont nécessaires pour écrire un fichier ".feature". Voici un exemple :

```gherkin
# language: fr

# Un fichier feature teste une fonctionnalité
Fonctionnalité: : titre de la fonctionnalité

Mon commentaire sur la fonctionnalité ou explication.

Scénario: Mon commentaire sur ce scénario spécifique

    Etant donné que je veux faire la recette "tarte aux légumes" et j'ai les ingrédients
    Et J'ai le matériel nécessaire
    Quand Je réalise les actions nécessaire à la recette
    Alors Je réussi la recette
```

Le mot clé `Fonctionnalité` DOIT entamer le fichier. Ce qui est avant ne sera pas pris en compte. Ce mot clé permet de décrire la fonctionnalité qui sera décrire par ce fichier.
On décrit une fonctionnalité par fichier. Ce mot clé ne peut pas être répété dans un même fichier.

Le mot clé `Scénario` décrit un cas d'usage. Cela peut être un cas nominal ou un cas d'erreur. Il est important que le mot clé scénario soit suivi d'un titre clair pour d'écrire le cas d'usage qui sera défini par la suite.

Entre les mots clé `Fonctionnalité` et `Scénario`, tout ce qui sera écrit ne sera pas exécuté.

Il est possible d'ajouter des informations supplémentaires sur une étape avec un tableau :

```gherkin
# language: fr
  
  Etant donné que J'ai appris les recettes suivantes :
    | NomRecette                | Ingredients                                | ModeCuisson       | Etapes                   | Exquis |
    | purée de pommes de terre  | POMME_DE_TERRE,BEURRE,LAIT                 | PLAQUE_DE_CUISSON | COUPER,CUIRE,MELANGER    | true   |
    | purée de carottes         | POMME_DE_TERRE,BEURRE,LAIT,CAROTTES,EPICES | PLAQUE_DE_CUISSON | COUPER,CUIRE,MELANGER    | true   |

  Et que je veux faire la recette "purée de pommes de terre"
  Et que j'ai un PLAQUE_DE_CUISSON
  Et J'ai les ingrédient suivant :
    | POMME_DE_TERRE |
    | BEURRE         |
    | LAIT           |
  Quand Je réalise l'action : COUPER
  Et Je réalise l'action : CUIRE
  Et Je réalise l'action : MELANGER
  Alors Je réussi la recette
```

Dans l'exemple précédent, l'étape `Et J'ai les ingrédient suivant :` est suivie d'une liste. On comprend donc l'on dispose des ingrédients présents dans la liste.

Et l'étape `Etant donné que J'ai appris les recettes suivantes :` est suivie d'un tableau avec sur la première ligne le nom des colonnes et ensuite les informations correspondantes. Dans l'exemple précédent, chaque ligne d'information correspond à une recette que le cuisinié a apprise.

## Gherkin et Cucumber

Cucumber est un framework utilisable avec plusieurs langages de programmation qui permet de lier des étapes GHERKIN à du code exécutable. Ce lien entre les étapes GHERKIN et du code éxécutable s'appelle la glue CUCUMBER.

Il faut donc définir des étapes GHERKIN compréhensible, lisible et réutilisable dans de nombre scénario pour éviter d'avoir à mettre en place beaucoup de glue CUCUMBER.

Il est donc important que les testeurs et les développeurs travail ensemble pour définir la granularité des étapes GHERKIN et savoir ce qui est faisable dans le langage de programmation.

### mise en place de cucumber

Ce projet a été développé avec JAVA, Maven, sur Intellij. Je vous conseille donc de télécharger Intellij si vous ne l'avez pas sur votre poste.

Dans Intellij, il faut installer les plugins suivants :

* CUCUMBER for java
* GHERKIN

Le second permet de reconnaitre le langage GHERKIN et donne donc des aides pour écrire un fichier ".feature"
Le premier permet de faire le lien entre la glue cucumber et le GHERKIN. On peut ainsi lancer des tests depuis un fichier ".feature" ou faire un ctrl+click sur une étape dans un fichier ".feature" pour aller directement voir la glue correspondante.

Pour la partie Maven, il faut récupérer les dépendances suivantes dans votre fichier POM

```xml
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>7.16.1</version>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit</artifactId>
            <version>7.16.1</version>
            <scope>test</scope>
        </dependency>
```

La première dépendance permettra de mettre en place la glue
La seconde permet de lancer les tests CUCUMBER avec la commande `mvn test`

```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.13.2</version>
    <scope>test</scope>
</dependency>
```

On aura aussi besoin de Junit pour lancer les tests et potentiellement utiliser les méthodes de type assert lors de nos étapes de vérification

### Lien étape → code

La classe `StepRecettes` contient toute la glue de ce projet. Un exemple sera plus efficace qu'une explication.

Imaginons le step :
```gherkin
# language: fr
Alors Je réussi la recette
```

La glue correspondante en java sera ainsi :
``` java
@Alors("Je réussi la recette")
public void nomDeMethode() {
//Faire des actions
}
```

Il existe des tags java pour chaque type de step GHERKIN. Mais le tag n'est pas important. Par exemple le step :
```gherkin
# language: fr
Quand Je réalise les actions nécessaire à la recette
```

Peut parfaitement utiliser la glue
``` java
@Etantdonnéque("Je réalise les actions nécessaire à la recette")
public void jeRéaliseLesActionsNécessaireÀLaRecette1() {
//Faire des actions
}
```
ou
``` java
@Quand("Je réalise les actions nécessaire à la recette")
public void jeRéaliseLesActionsNécessaireÀLaRecette2() {
//Faire des actions
}
```
ou
``` java
@Alors("Je réalise les actions nécessaire à la recette")
public void jeRéaliseLesActionsNécessaireÀLaRecette3() {
//Faire des actions
}
```

ATTENTION : si vous avez les 3 méthodes précédentes dans la glue, CUCUMBER ne sera pas la méthode à prendre en cas d'utilisation du step. Le scénario tombera alors en erreur.

### Variable et tableau

Il est possible pour réduire la quantité de glue à créer de mettre en place des variables et des tableaux dans les étapes.

Voici les différents types de variable (je laisse le tableau en anglais)

| Type          | Description |
|---------------| ----------- |
| `{int}`       | Matches integers, for example `71` or `-19`. Converts to a 32-bit signed integer if the platform supports it.|
| `{float}`     | Matches floats, for example `3.6`, `.8` or `-9.2`. Converts to a 32 bit float if the platform supports it. |
| `{word}`      | Matches words without whitespace, for example `banana` (but not `banana split`). |
| `{string}`    | Matches single-quoted or double-quoted strings, for example `"banana split"` or `'banana split'` (but not `banana split`). Only the text between the quotes will be extracted. The quotes themselves are discarded. Empty pairs of quotes are valid and will be matched and passed to step code as empty strings. |
| `{}` anonymous | Matches anything (`/.*/`). |
| `{bigdecimal}` | Matches the same as `{float}`, but converts to a `BigDecimal` if the platform supports it. |
| `{double}`    | Matches the same as `{float}`, but converts to a 64 bit float if the platform supports it. |
| `{biginteger}` | Matches the same as `{int}`, but converts to a `BigInteger` if the platform supports it. |
| `{byte}`      | Matches the same as `{int}`, but converts to an 8 bit signed integer if the platform supports it. |
| `{short}`     | Matches the same as `{int}`, but converts to a 16 bit signed integer if the platform supports it. |
| `{long}`      | Matches the same as `{int}`, but converts to a 64 bit signed integer if the platform supports it. |

Vous pouvez retrouver ce tableau et un peu plus d'information sur la page suivante : https://github.com/cucumber/cucumber-expressions/tree/main

Voici un exemple d'utilisation de variable
``` java
@Et("je veux faire la recette {string}")
public void jeVeuxFaireLaRecette(String recette) {
//action
}
``` 

On voit que la glue contient une variable de type string. On récupère automatiquement ce string dans la signature de la méthode.

L'étape Gherkin correcpondante sera alors ainsi :
```gherkin
# language: fr
Etant donné que je veux faire la recette "plat surgelé"
```

Si on a plusieurs variables, on récupère les variables dans l'ordre dans la signature de la méthode
``` java
@Et("j'ai {int} cookie(s) et {int} chocolat(s)")
public void jaiCookieEtChocolat(int nbCookies, int nbChocolat) {
//action
}
``` 

Dans ce second exemple, on écrira le step ainsi :
```gherkin
# language: fr
Etant donné que j'ai 3 cookies et 1 chocolats
```

**TIPS** : Vous avez peut-être remarqué quelques chose d'étrange dans cette étape. Il n'y a pas les parenthèses. Dans la glue, les parenthèses renferment un ou des caractères optionnels. L'étape sera reconnu par la glue avec ou sans ces caractères.

``` java
@Etantdonnéque("J'ai appris les recettes/plats suivant(e)s :")
public void jAiApprisLesRecettesSuivantes() {
```

**TIPS** : Dans la glue précédente, voici un petit tips pour rendre plus lisible certaine étape. Le caractère "/" permet de proposer 2 possibilités pour écrire l'étape :
```gherkin
# language: fr
Etant donné que J'ai appris les plats suivants :
```
```gherkin
# language: fr
Etant donné que J'ai appris les recettes suivantes :
```

Enfin pour les listes et les tableaux le traitement renverra un unique et même objet

``` java
@Etantdonnéque("J'ai les ingrédients suivant :")
public void jAiLesIngredientSuivant(DataTable table) {
List<String> rowIngredients = table.asList(String.class);
```

L'objet DataTable devra toujours être à la fin de la signature de la méthode.
Cet objet peut être parcouru de différentes manières, mais la manière la plus simple et de transformé cet objet en list, en map, en list de list ou en map de map. Je vais vous présenter les cas les plus courants, mais voici un lien si, vous souhaitez creuser la question : https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-java#data-tables

Dans l'exemple précédent, on fait la conversion à la main. Mais pour faire une conversion, il faut que le tableau ait le bon format et on peut demander de faire la transformation directement dans la signature de la méthode. Voici comment :
``` java
@Etantdonnéque("J'ai les ingrédients suivant :")
public void jAiLesIngredientSuivant(List<String> table) {
```

Oui, il suffit vraiment de faire cela

Voici les différentes conversions possibles et le format de tableau nécessaire pour éviter d'avoir une erreur lors de la conversion.

* `List<List<String>> table`
* `List<Map<String, String>> table`
* `Map<String, String> table`
* `Map<String, List<String>> table`
* `Map<String, Map<String, String>> table`

Voici les différentes étapes permettant de faire ces convertions :
```gherkin
# language: fr
Quand Etape list
| valeur1 |
| valeur2 |
| valeur3 |
Quand Etape map
| cle1 | valeur1 |
| cle2 | valeur2 |
| cle3 | valeur3 |
Quand Etape list de maps
| cle1     | Cle2    | Cle3     |
| valeur11 |valeur12 | valeur13 |
| valeur21 |valeur22 | valeur23 |
Quand Etape map de list
| cle1 | valeur11 | valeur21 |
| cle2 | valeur12 | valeur22 |
| cle3 | valeur13 | valeur23 |
Quand Etape map de maps
|        | colonne1 | colonne2 |
| ligne1 | valeur11 | valeur12 |
| ligne2 | valeur21 | valeur22 |
```

Et voici la glue correspondante :
``` java
    @Quand("Etape list")
    public void etapeList(List<String> table) {
        System.out.println(table);
    }

    @Quand("Etape map")
    public void etapeMap(Map<String, String> table) {
        System.out.println(table);
    }

    @Quand("Etape list de maps")
    public void etapeListDeMaps(List<Map<String, String>> table) {
        System.out.println(table);
    }

    @Quand("Etape map de list")
    public void etapeMapDeList(Map<String, List<String>> table) {
        System.out.println(table);
    }

    @Quand("Etape map de maps")
    public void etapeMapDeMaps(Map<String, Map<String, String>> table) {
        System.out.println(table);
    }
```

Si vous tentez de mettre le tableau de `Etape map` dans l'autre sens, vous aurez une erreur. Si vous mettez une valeur dans la première case du tableau dans `Etape map de maps` vous aurez une erreur. Il est donc important de bien définir le format que le tableau aura avec les testeurs pour éviter d'avoir plein d'erreur avec la glue.

## Création de vos propres variables

Vous pouvez trouver dans ce projet la glue suivante :

``` java
@Quand("Je réalise l'action : {etape}")
public void jeRealiseAction(EtapeRealisation etape) {
//Action
}
```

La variable `{etape}` ne correspond à aucune variable que j'ai présenté. C'est tout simplement parce que cette variable est une variable qui est défini dans la glue avec le code suivant :
``` java
@ParameterType("COUPER|MELANGER|ATTENDRE|CUIRE")  // regexp
public EtapeRealisation etape(String etape){
return EtapeRealisation.valueOf(etape);
}
```

L'annotation `@ParameterType` permet de définir un nouveau type de variable. Le nom de la variable `{etape}` correspond au nom de la méthode `etape`. Cette variable particulière permet 2 choses :
* Un regex défini les strings qui sont autorisés ou non. l'étape ne sera pas reconnu par la glue si notre variable ne correspond pas au regex.
* La méthode peut renvoyer n'importe quel objet java.
* 
Dans l'exemple, la variable n'autorise que 4 valeurs et ensuite renvoie un enum.

Voici un exemple qui va un peu plus loin :
```java
@ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
public LocalDate iso8601Date(String year, String month, String day) {
return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
}
```

Les regex permettent de définir des groupes de caractères. On peut récupérer les groupes ainsi formés directement dans la signature de la méthode.

Enfin, il est aussi possible de transformer automatiquement un tableau de donnée GHERKIN en un objet java ou une liste d'objet java. Voici un exemple :
```java
    @Etantdonnéque("J'ai appris les plats suivants : \\(convertion auto)")
    public void jAiApprisLesPlatsSuivantsConvertionAuto(List<Recette> recettes) {
        // Mon code qui utilise la liste de recette
    }

    @DataTableType
    public Recette recetteEntryTransformer(Map<String, String> table) {

        // Code permettant de convertir une map en recette
        // Puis on retourne une recette
        return new Recette(/*Information pour le construteur*/);
    }
```

L'annotation `@DataTableType` permet de définir un nouveau convertisseur pour transformer une list de string ou une map de string en un objet java. Dans notre cas, on transforme une map en objet recette.

Lorsqu'on utilise ce convertisseur, il suffit d'avoir le bon type de tableau.

Dans notre exemple notre transformateur transforme Map<String, String> -> Recette

On peut alors écrire une étape avec un tableau de ce type pour utiliser notre convertisseur :
```gherkin
# language: fr
Quand mon étape
| cle1 | valeur1 |
| cle2 | valeur2 |
| cle3 | valeur3 |
```
On aurait alors une glue java de ce type :
```java
@Quand("mon étape")
public void monEtape (Recette recette) {
// Le code qui va bien
}
```

On peut aussi avoir un tableau de ce type qui utilisera aussi notre convertisseur :
```gherkin
# language: fr
Quand mon étape
| cle1     | Cle2    | Cle3     |
| valeur11 |valeur12 | valeur13 |
| valeur21 |valeur22 | valeur23 |
```
On aurait alors une glue java de ce type :
```java
@Quand("mon étape")
public void monEtape(List<Recette> recettes) {
// Le code qui va bien
}
```

Et enfin, on peut avoir ce cas de figure qui utilisera encore notre convertisseur :
```gherkin
# language: fr
Quand mon étape
|        | colonne1 | colonne2 |
| ligne1 | valeur11 | valeur12 |
| ligne2 | valeur21 | valeur22 |
```
On aurait alors une glue java de ce type :
```java
@Quand("mon étape")
public void monEtape(Map<String, Recette> recettes) {
// Le code qui va bien
}
```

## Lancer les tests avec maven

La classe suivante permet de lancer les tests Cucumber :
```java
@RunWith(Cucumber.class)
@CucumberOptions(
plugin = "pretty",
features = "classpath:features",
glue = "classpath:org.example.glue")
class RunCucumberTest {
}
```

Cette classe a `Test` dans son nom. Elle est donc scruté lorsqu'on lance la commande `mvn test`.

On indique bien qu'on lance des tests Cucumber avec un runner Cucumber avec le tag `@RunWith(Cucumber.class)`.
Le tag `@CucumberOptions` permet de définir comment le runner va fonctionner, le format de sortie du rapport d'exécution et où il va chercher la glue et les fichiers ".feature". Les 2 options importantes dans cette exemple sont donc :
* `features = "classpath:features"` -> défini le chemin vers le ou les fichiers features
* `glue = "classpath:org.example.glue"` -> défini le chemin vers la glue


## Bonnes pratiques

La chose la plus importante est le dialogue entre les testeurs qui vont écrire le GHERKIN et les développeurs qui développeront la glue CUCUMBER.
Il faut définir ensemble la granularité des étapes en gardant en tête que ces étapes doivent être compréhensible à la première lecture et doivent être réutilisable dans le plus de scénario possible.
Dans l'idéal, la lecture des scénarios par une personne extérieur doit permettre de comprendre le fonctionnement du système.

Il est important de définir les variables qui seront utilisées.
Les développeurs doivent faire attention à bien indiquer les limites de ces variables au testeurs et vérifier que cela correspond bien à leurs besoins.

Il faut bien définir les tableaux.
Est-ce que leur dimension est fixe ou non ? Est-ce qu'on met un titre aux colonnes ? Est-ce qu'un tableau correspondra toujours au même type d'objet ?
Si un tableau devient trop gros et est illisible, il est souvent préférable de créer plusieurs étapes qui permettront peut-être simplement d'enregistrer les nombreux entrant avant d'avoir une étape qui va amalgamer toutes ces données et les traiter ou construire un objet java avec.

Où allez-vous conserver vos scénarios ?
Moi, je préfère les garder dans git car les scénarios vont évoluer avec les évolutions du système et on peut ensuite envoyer les scénarios et le résultat de l'éxécution de ces scénarios sur les endpoint exposé par x-ray.
Cette question doit être soumise à l'ensemble de l'équipe pour peser le pour et le contre.

Pour les développeurs :
* Vous pouvez utiliser plusieurs classes java différentes pour définir votre glue et l'organiser.
* Il est souvent interressant d'avoir une classe de contexte qui garde en mémoire les actions du scénario en cours. Cela permet généralement de ce facilité la vie lors des étapes de vérification.
* Mettre le moins d'intelligence possible dans la classe qui défini les étapes (step). Rester sur la mise en forme des tableaux GHERKIN et les variables GHERKIN.

Vous avez maintenant les informations de base pour commencer à utiliser le GHERKIN et CUCUMBER.

## Exercice

Je vous laisse un petit bout de code à tester. Vous allez tester une librairie. Cette librairie contient des livres avec chacun un titre, un prix, un auteur, un éditeur et un genre. Le genre est un enum et l'éditeur une chaine de caractère sans espace ni caractère spéciaux.
La librairie permet de faire des recherches dans les livres. On peut chercher par titre et on considère que 2 livre ne peuvent pas avoir le même titre. On peut faire une recherche par auteur et on aura potentiellement plusieurs livres et une recherche par genre et là aussi on aura potentiellement plusieurs livres.

Les étapes doivent être créer dans le fichier testLib.feature
La glue doit être créé dans la classe StepLibrairie.

Premier exercice :
Créer un constructeur dans la classe librairie et ajouter quelques livres dans le constructeur qui vont remplir la map de l'objet libraire (la clé correspond au titre du livre).
Ensuite, il faut créer les étapes suivantes :
```gherkin
# language: fr
Etant donné que je trouve une librairie
Quand Je veux acheter le livre "titre du livre"
Alors j'achète le livre avec 20 euros
```

* La première étape créer l'objet librairie. Il faudra stocker cet objet dans un objet context.
* La seconde étape fait une recherche pour trouver le livre que l'on souhaite acheter. On a donc une variable pour le titre du livre
* La troisième étape sort positive si la librairie possède le livre et que j'ai assé d'argent pour l'acheter. Il faut aussi une variable pour l'argent.

Second exercice : 
Refaire le même test mais il faut que l'étape de création de la librairie est plusieurs variable permettent de définir un livre. Utilisé pour le moment des variables simples (string, word, double).
Oui la librairie n'aura qu'un livre.

Troisième exercice : 
Reprendre le scénario précédent et n'autorisé que les valeurs de l'enum pour la variable du genre du livre.

Quatrième exercice :
refaire l'étape de création de la librairie avec un tableau qui contiendra tous les livres de la librairie. Ne pas utiliser pour le moment l'annotation `@DataTableType`

Cinquième exercice :
Utiliser maintenant le tag `@DataTableType` pour convertir automatiquement chaque ligne du tableau en livre.

Sixième exercice :
Faire une étape : Quand je cherche tous les livres de cette auteur "nom de l'auteur"
Cette étape remonte donc potentiellement plusieurs livre.
Faire une autre étape : Alors je trouve les livres suivant :
Cette étape est suivi d'un tableau de livre et vérifier ensuite que ce tableau correspond au résultat de l'étape précédente.

Septième exercice : 
Lancer vos scénarios avec la classe RunCucumberTest

Bonne journée