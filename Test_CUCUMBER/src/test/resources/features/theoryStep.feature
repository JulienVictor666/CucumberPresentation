# language: fr

# Un fichier feature teste une fonctionnalité
Fonctionnalité: titre de la fonctionnalité

  Mon commantaire sur la fonctionnalité ou explication.

  Scénario: Mon commentaire sur ce scénario spécifique

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