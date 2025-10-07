### Exercice Avancé : Gestion d'une Bibliothèque avec des Streams en Java

#### Classe `Livre`

```java
    private String titre;
    private String auteur;
    private String genre;
    private LocalDate datePublication;
    private int nbPages;
    private boolean estDisponible;
    private double prix;

```

---

### Objectifs de l'exercice

Vous devez implémenter les différentes tâches suivantes en utilisant des **Streams** :

1. **Filtrer les livres disponibles** (dont `estDisponible` est `true`).
2. **Trouver les livres publiés avant 1950** et afficher leur titre et auteur.
3. **Grouper les livres par genre** (par exemple, tous les "Romans" ensemble, tous les "Science-fiction" ensemble, etc.).
4. **Trouver le livre le plus ancien** dans la bibliothèque (le livre avec la date de publication la plus ancienne).
5. **Vérifier s'il existe des livres dont le titre commence par "Harry"**.
6. **Calculer le prix moyen des livres** disponibles dans la bibliothèque.
7. **Trier les livres par nombre de pages**, puis par prix (en cas d'égalité du nombre de pages).
8. **Calculer le total des pages** pour tous les livres de genre "Fantasy".
9. **Trouver le livre le plus cher** disponible à la vente.
10. **Trouver les auteurs les plus prolifiques** (les auteurs ayant écrit plus de 17 livre) et afficher leurs livres.
11. **Compter le nombre de livres dans chaque genre** (afficher par exemple combien de "Romans", combien de "Science-fiction", etc.).
12. **Vérifier si un livre est disponible et si son prix est inférieur à un seuil donné**, et afficher les livres correspondants.
13. **Calculer le nombre total de pages** pour les livre de chaque année.




