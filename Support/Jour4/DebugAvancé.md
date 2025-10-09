
##  Objectif du mode Debug dans IntelliJ IDEA

La **perspective Debug** (ou “vue Debug”) te permet de :

* **exécuter ton programme pas à pas** (step by step) ;
* **analyser** les valeurs des variables, objets et threads ;
* **modifier l’exécution en temps réel** ;
* **comprendre le flux du programme**, les exceptions et les threads.

---

##  1. Vue d’ensemble du débogueur IntelliJ

Quand tu lances un programme en **Debug** (`Shift + F9`), IntelliJ ouvre plusieurs panneaux :

| Panneau         | Rôle                                                                   |
| --------------- | ---------------------------------------------------------------------- |
| **Debugger**    | contrôle de l’exécution (play, pause, step, etc.)                      |
| **Frames**      | pile d’appels (stack trace) du thread courant                          |
| **Variables**   | affiche les variables locales, leurs champs et objets imbriqués        |
| **Watches**     | expressions que tu veux surveiller manuellement                        |
| **Console**     | sortie standard, avec parfois un REPL interactif (Evaluate Expression) |
| **Breakpoints** | liste et gestion de tous les points d’arrêt                            |

---

##  2. Les breakpoints avancés

IntelliJ ne se limite pas aux “breakpoints simples”. Il propose plusieurs **types puissants** :

###  a) **Conditional Breakpoints (points d’arrêt conditionnels)**

➡ Arrête le programme **seulement si une condition spécifique est vraie**.

**Exemple :**
Tu veux t’arrêter quand `i > 100`.

* Clique sur le point rouge (breakpoint)
* Clic droit → “**Condition**”
* Écris : `i > 100`

 Tu peux aussi utiliser des expressions complexes :
`user != null && user.getRole().equals("ADMIN")`

---

###  b) **Log Breakpoints**

➡ Ne pas interrompre le programme, mais **afficher un message dans la console**.

Très utile pour **observer sans bloquer**.

* Clic droit sur le breakpoint
* Coche “Log message to console”
* Ajoute un message comme :
  `"At iteration: " + i`

 Alternative rapide au `System.out.println()` — sans modifier ton code !

---

###  c) **Method Breakpoints**

➡ Déclenchés **à l’entrée ou à la sortie d’une méthode**, même sans code visible.

Très pratique pour suivre :

* des méthodes d’API externes ;
* des méthodes d’interfaces ou abstraites.

 Attention : ils peuvent **ralentir l’exécution** car le débogueur surveille toutes les entrées/sorties de la méthode.

---

### d) **Field Watchpoints**

➡ Surveille la **lecture** ou la **modification** d’un champ.

Exemple :

```java
private int score;
```

* Clique à gauche de la déclaration de `score`.
* Dans les propriétés du breakpoint, coche :

  * “Field access” (lecture)
  * “Field modification” (écriture)

 Le débogueur s’arrête à chaque changement de `score`.

---

###  e) **Exception Breakpoints**

➡ Le programme s’arrête **dès qu’une exception est levée** (même si elle est attrapée).

* Ouvre la vue “Breakpoints” (`Ctrl + Shift + F8`)
* Clique sur le “+” → **Java Exception Breakpoint**
* Choisis par ex. `NullPointerException`

 Très utile pour trouver **où** une exception est lancée.

---

## 3. Contrôle de l’exécution

| Commande            | Raccourci                  | Description                                                                    |
| ------------------- | -------------------------- | ------------------------------------------------------------------------------ |
| **Step Over**       | `F8`                       | Exécute la ligne entière sans entrer dans les méthodes                         |
| **Step Into**       | `F7`                       | Entre dans la méthode appelée                                                  |
| **Smart Step Into** | `Shift + F7`               | Te laisse choisir quelle méthode appeler (quand plusieurs sur la même ligne)   |
| **Step Out**        | `Shift + F8`               | Termine la méthode courante et revient à l’appelant                            |
| **Run to Cursor**   | `Alt + F9`                 | Exécute jusqu’à la ligne où se trouve ton curseur                              |
| **Drop Frame**      | Aucun raccourci par défaut | Revient au début de la méthode courante (relance sans redémarrer le programme) |
| **Resume**          | `F9`                       | Continue jusqu’au prochain breakpoint                                          |

 “**Drop Frame**” est une fonctionnalité avancée très pratique :

> Elle te permet de **ré-exécuter une méthode** sans relancer tout le programme.

---

## 4. Inspection des variables et expressions

### a) **Variables View**

Tu peux :

* développer les objets (afficher les champs internes) ;
* copier leur valeur (`Ctrl + C`) ;
* modifier une valeur en cours d’exécution (`Alt + clic` sur la valeur).

---

### b) **Evaluate Expression** (`Alt + F8`)

Ouvre une fenêtre interactive où tu peux :

* évaluer des expressions Java :
  `user.getName().toUpperCase()`
* appeler des méthodes ;
* inspecter le résultat instantanément.

 Tu peux même **modifier l’état d’un objet** via Evaluate Expression :

```java
user.setActive(false);
```

---

##  6. Debug multi-threads

Dans la vue **Frames**, IntelliJ affiche **tous les threads actifs** :

* Chaque thread a sa pile d’appels ;
* Tu peux basculer entre eux pour **examiner leur état** ;
* Tu peux suspendre un thread individuellement ou tous les threads.

 En bas du panneau Debug → bouton “**Thread Selector**”.

