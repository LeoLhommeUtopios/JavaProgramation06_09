## Contexte :
Vous travaillez sur une application de gestion de bibliothèque permettant aux utilisateurs de rechercher, emprunter, et retourner des livres.
Votre objectif est d'améliorer la maintenance et la surveillance de l'application en intégrant un système de logging et de mesure des performances pour les opérations clés.

- On se limitera à un service BookService qui permet de créer, supprimer, récupérer des livres.

# Implémentation de l'Aspect :

- Créer un aspect LoggingAspect pour logger l'entrée et la sortie de chaque méthode de LibraryService, incluant les arguments de méthode et les valeurs de retour (Un Logger en stdout).
- Créer un aspect PerformanceAspect pour mesurer le temps d'exécution de chaque méthode de LibraryService. Utiliser System.currentTimeMillis() pour calculer le temps d'exécution.

# Partie 2

- Implémenter les aspect de la partie 1 à l'aide d'annotations (Log et Performance)
