# Banque jpa

### Ennoncé:
En utilisant les éléments JPA, il faudra créer une application en ligne de commande qui vous permettra de gérer les comptes d’une personne.

Une agence bancaire dispose de plusieurs comptes en banque. Chacune de ces comptesen banque est rattaché à une seule agence.

Chaque client peut disposer de plusieurs comptes en banque.Malgré la particularité de la situation, des comptes en banque peuvent appartenir à plusieurs personnes (membre de la famille par exemple.).

>- Un Customer se compose des éléments suivants:
>>- id
>>- lastName
>>- firstName
>>- birthDate
>>- List Account

>- Un Account se compose des éléments suivants:
>>- id
>>- libelle
>>- IBAN
>>- Balance
>>- Agency
>>- list Customer

>- Une Agency se compose des éléments suivant:
>>- id
>>- adresse 

### Relation :
>- Customer :
>>- Relation Many to many avec Account (bidirectional)

>- Account :
>>- Relation many to many avec Customer (bidirectional)
>>- Relation many to one avec Agency (1 Agency / many Account) (unidirectional)

>- Agency:
>>-Relation one to many avec Account (many Account / 1 Agency ) (unidirectional)

>- Accout_Customer
>>- table de liaison entre account et customer 


### menu :
        1-- creation d'utilisateur
        2-- suppresion d'utilisateur
        3-- creation de compte
        4-- suppression de compte
        5-- creation d'agence
        6-- suppresion d'agence
        7-- ajouter agence a un compte (permet d'ajouter a un compte une agence cree precedament)
        8-- ajouter utilisateur a un compte (permet d'ajouter a un compte un utilisateur cree precedament)
        9-- suppresion d'un utilisateur d'un compte (permet de retirer a un compte un utilisateur)
        0-- quitter