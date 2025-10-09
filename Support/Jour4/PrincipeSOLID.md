

## Les 5 principes **SOLID** en Java

| Lettre | Nom complet                     | En français                |
| :----: | :------------------------------ | :------------------------- |
|  **S** | Single Responsibility Principle | Responsabilité unique      |
|  **O** | Open/Closed Principle           | Ouvert/Fermé               |
|  **L** | Liskov Substitution Principle   | Substitution de Liskov     |
|  **I** | Interface Segregation Principle | Ségrégation des interfaces |
|  **D** | Dependency Inversion Principle  | Inversion des dépendances  |

---

###  1. **S — Single Responsibility Principle (SRP)**

> Une classe ne doit avoir **qu’une seule raison de changer**.

Chaque classe doit avoir **une seule responsabilité**.
Cela rend ton code plus clair et plus facile à maintenir.

**Exemple sans SRP :**

```java
public class Report {
    public void generateReport() {
        // logique de génération du rapport
    }

    public void saveToPDF() {
        // logique pour sauvegarder en PDF
    }

    public void sendByEmail() {
        // logique pour envoyer par email
    }
}
```

 Ici, la classe fait **trop de choses** (génération, export, envoi).

**Avec SRP appliqué :**

```java
public class Report {
    public void generateReport() {
        // génération du rapport
    }
}

public class ReportSaver {
    public void saveToPDF(Report report) {
        // sauvegarde PDF
    }
}

public class EmailSender {
    public void sendByEmail(Report report) {
        // envoi email
    }
}
```

 Chaque classe a **une seule responsabilité**.

---

### 🔹 2. **O — Open/Closed Principle (OCP)**

> Les classes doivent être **ouvertes à l’extension** mais **fermées à la modification**.

Tu dois pouvoir **ajouter de nouvelles fonctionnalités** sans changer le code existant.

**Mauvais exemple :**

```java
public class AreaCalculator {
    public double calculateArea(Object shape) {
        if (shape instanceof Circle) {
            Circle c = (Circle) shape;
            return Math.PI * c.getRadius() * c.getRadius();
        } else if (shape instanceof Rectangle) {
            Rectangle r = (Rectangle) shape;
            return r.getWidth() * r.getHeight();
        }
        return 0;
    }
}
```

 Chaque fois qu’on ajoute une nouvelle forme, on **modifie** la classe.

**Bon exemple (OCP appliqué) :**

```java
public interface Shape {
    double area();
}

public class Circle implements Shape {
    private double radius;
    public Circle(double radius) { this.radius = radius; }
    public double area() { return Math.PI * radius * radius; }
}

public class Rectangle implements Shape {
    private double width, height;
    public Rectangle(double width, double height) { this.width = width; this.height = height; }
    public double area() { return width * height; }
}

public class AreaCalculator {
    public double calculateArea(Shape shape) {
        return shape.area();
    }
}
```

 Si tu veux une nouvelle forme, tu **crées une nouvelle classe**, pas besoin de toucher aux anciennes.

---

###  3. **L — Liskov Substitution Principle (LSP)**

> Une sous-classe doit pouvoir **remplacer** sa classe mère **sans modifier le comportement attendu**.

**Mauvais exemple :**

```java
public class Bird {
    public void fly() {
        System.out.println("Je vole !");
    }
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Les pingouins ne volent pas !");
    }
}
```

 Ici, le `Penguin` ne respecte pas le contrat du parent (`fly()` doit marcher).

**Bon exemple (LSP respecté) :**

```java
public interface Bird {
    void eat();
}

public interface FlyingBird extends Bird {
    void fly();
}

public class Sparrow implements FlyingBird {
    public void eat() { System.out.println("Le moineau mange"); }
    public void fly() { System.out.println("Le moineau vole"); }
}

public class Penguin implements Bird {
    public void eat() { System.out.println("Le pingouin mange"); }
}

```

 Plus de problème de substitution.

---

### 4. **I — Interface Segregation Principle (ISP)**

> Les interfaces doivent être **petites et spécifiques**,
> pas de “grosses interfaces” que toutes les classes doivent implémenter.

**Mauvais exemple :**

```java
public interface Worker {
    void work();
    void eat();
}

public class Robot implements Worker {
    public void work() { System.out.println("Le robot travaille"); }
    public void eat() { /* inutile */ }
}
```

**Bon exemple (ISP appliqué) :**

```java
public interface Workable {
    void work();
}

public interface Eatable {
    void eat();
}

public class Human implements Workable, Eatable {
    public void work() { System.out.println("L'humain travaille"); }
    public void eat() { System.out.println("L'humain mange"); }
}

public class Robot implements Workable {
    public void work() { System.out.println("Le robot travaille"); }
}
```

 Chaque classe n’implémente que ce dont elle a besoin.

---

### 🔹 5. **D — Dependency Inversion Principle (DIP)**

> Les classes de haut niveau ne doivent pas dépendre de classes de bas niveau,
> mais **toutes deux doivent dépendre d’abstractions**.

**Mauvais exemple :**

```java
public class MySQLDatabase {
    public void save(String data) {
        System.out.println("Saving data to MySQL");
    }
}

public class UserService {
    private MySQLDatabase database = new MySQLDatabase();

    public void saveUser(String data) {
        database.save(data);
    }
}
```

 `UserService` dépend directement de `MySQLDatabase`.

**Bon exemple (DIP appliqué) :**

```java
public interface Database {
    void save(String data);
}

public class MySQLDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving data to MySQL");
    }
}

public class MongoDatabase implements Database {
    public void save(String data) {
        System.out.println("Saving data to MongoDB");
    }
}

public class UserService {
    private Database database;

    public UserService(Database database) {
        this.database = database;
    }

    public void saveUser(String data) {
        database.save(data);
    }
}
```

 `UserService` dépend d’une **interface**, pas d’une implémentation concrète.
Tu peux facilement remplacer MySQL par MongoDB sans toucher à la logique.

---

### Récapitulatif

| Principe | Idée clé                                      | Objectif                             |
| -------- | --------------------------------------------- | ------------------------------------ |
| **S**    | Une seule responsabilité                      | Simplifier et isoler les changements |
| **O**    | Ouvert à l’extension, fermé à la modification | Ajouter sans casser                  |
| **L**    | Substituabilité des sous-classes              | Respecter le contrat du parent       |
| **I**    | Interfaces spécifiques                        | Éviter les interfaces trop larges    |
| **D**    | Dépendance envers des abstractions            | Rendre le code flexible et testable  |

