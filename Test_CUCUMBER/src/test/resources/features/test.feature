# language: fr

# Un fichier feature teste une fonctionnalité
Fonctionnalité: préparation recette

  Mon commantaire sur la fonctionnalité.

  Scénario: Test réalisation d'un plat surgelé

    # On prend une {string} dans cette étape. Si on avait pris une variable de type {word} dans cette étape, pas besoin de guillement mais on ne peut pas autoriser les espaces dans ce cas
    Etant donné que je veux faire la recette "plat surgelé"
    # On autorise une variable qui doit correspondre à un regex. Si la variable ne correspond pas l'étape n'est pas reconnu
    # https://github.com/cucumber/cucumber-expressions/tree/main
    Et que j'ai un MICRO_ONDE
    # On prend une liste d'élément en entré
    Et J'ai les ingrédients suivant :
      | SACHET_SURGELE |
    Quand  Je réalise l'action : CUIRE
    Alors  Je réussi la recette


  Scénario: Une étape renvoi une erreur.

  Si une étape renvoi une erreur on ne fait pas les autres étapes de ce scénario mais on fait les autre scénario

    # Cette première étape à une vérification et peut sortir en erreur et donc arrêter le reste du test.
    # Comme cette étape n'est pas normalement une étape de test je préfére sortir avec une exception
    Etant donné que je veux faire la recette "soupe à la tomate"
    Et que j'ai un MICRO_ONDE
    Et J'ai les ingrédients suivant :
      | SACHET_SURGELE |
    Quand  Je réalise l'action : CUIRE
    Alors  Je réussi la recette

  Scénario: La glue et les mots clé

  Les mot clé utilisé dans la glue et dans le Gherkin n'ont pas d'importance pour l'éxécution. Une étape Alors et utilisé en Etant donnée que

    # l'étape j'ai réussi la recette est normalement une étape Alors. Il faut éviter ce genre de chose mais CUCUMBER ne vas pas dire que cela est une erreur.
    # on se retrouve alors avec une erreur que l'on ne comprend pas obligatoirement

    Etant donné que je veux faire la recette "plat surgelé"
    Et que j'ai un MICRO_ONDE
    Etant donné que Je réussi la recette
    Et J'ai les ingrédients suivant :
      | SACHET_SURGELE |
    Quand  Je réalise l'action : CUIRE
    Alors  Je réussi la recette



  Scénario: Test réalisation d'une brioche avec un ingrédient en moins

    Etant donné que je veux faire la recette "brioche"
    Et que j'ai un FOUR
    # J'oublie le sucre
    Et J'ai les ingrédients suivant :
      | FARINE    |
      | BEURRE    |
      | OEUFS     |
      | LEVURE    |
      | LAIT      |
      | CHOCOLAT  |
    Quand Je réalise l'action : MELANGER
    Et Je réalise l'action : ATTENDRE
    Et Je réalise l'action : CUIRE
    Alors Je réussi la recette

  Scénario: Ajout d'une recette et réalisation

    # Dans la glue de cette étape on utilise la convertion automatique du dataTable
    # https://cucumber.io/docs/cucumber/configuration/?lang=java
    # https://github.com/cucumber/cucumber-jvm/tree/main/cucumber-java#data-tables
    Etant donné que J'ai appris les plats suivants :
      |                           | Ingredients                                | ModeCuisson       | Etapes                   | Exquis |
      | purée de pommes de terre  | POMME_DE_TERRE,BEURRE,LAIT                 | PLAQUE_DE_CUISSON | COUPER,CUIRE,MELANGER    | true   |
      | purée de carottes         | POMME_DE_TERRE,BEURRE,LAIT,CAROTTES,EPICES | PLAQUE_DE_CUISSON | COUPER,CUIRE,MELANGER    | true   |

    Et que je veux faire la recette "purée de pommes de terre"
    Et que j'ai un PLAQUE_DE_CUISSON
    Et J'ai les ingrédients suivant :
      | POMME_DE_TERRE |
      | BEURRE         |
      | LAIT           |
    Quand Je réalise l'action : COUPER
    Et Je réalise l'action : CUIRE
    Et Je réalise l'action : MELANGER
    Alors Je réussi la recette

  Scénario: Ajout d'une recette et réalisation avec conversion automatique

    Etant donné que J'ai appris les plats suivants : (convertion auto)
      | NomRecette                | Ingredients                                | ModeCuisson       | Etapes                   | Exquis |
      | purée de pommes de terre  | POMME_DE_TERRE,BEURRE,LAIT                 | PLAQUE_DE_CUISSON | COUPER,CUIRE,MELANGER    | true   |
      | purée de carottes         | POMME_DE_TERRE,BEURRE,LAIT,CAROTTES,EPICES | PLAQUE_DE_CUISSON | COUPER,CUIRE,MELANGER    | true   |

    Et que je veux faire la recette "purée de carottes"
    Et que j'ai un PLAQUE_DE_CUISSON
    Et J'ai les ingrédients suivant :
      | POMME_DE_TERRE |
      | BEURRE         |
      | LAIT           |
      | CAROTTES       |
      | EPICES         |
    Quand Je réalise l'action : COUPER
    Et Je réalise l'action : CUIRE
    Et Je réalise l'action : MELANGER
    Alors Je réussi la recette