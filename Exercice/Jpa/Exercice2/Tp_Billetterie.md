## Tp Billetterie

- Nous voullon crée une application de gestion de billets pour des evenements :

- nos classe seront :
    - adresse 
        - rue
        - ville 
        - l'adresse a une relation one to one avec les clients et les evenements

    
   - billet :
        - numeros de place
        - client
        - Evenement
        - type de place (standar , gold, vip)


   - Evenement :
        - nom
        - adresse 
        - date
        - heure
        - nombre de place


  - Client :
     - nom
     - prenom
     - adresse 
     - age 
     - numeros de telephone



---
  
1. Réservation de Billets :

- Nos clients pourront réserver des billets pour différents événements, et chaque billet sera lié à un événement.

2. Gestion des Événements :

- Pour chaque événement, il sera possible de récupérer la liste des billets associés.

3. Interface Homme-Machine (IHM) :

- Nous souhaitons une interface utilisateur permettant de réaliser les opérations CRUD (Créer, Lire, Mettre à jour, Supprimer) pour chaque entité.

4. Bonus : Vérification des Places Disponibles :

- Lors de la réservation d'un billet pour un événement, nous voulons vérifier si l'événement a encore des places disponibles.


