# Étape 1 : Construction de l'application
FROM maven:3.9.4-amazoncorretto-21 AS build
 
# Définit le répertoire de travail pour Maven
WORKDIR /app
 
# Copie les fichiers pom.xml et les fichiers sources pour télécharger les dépendances
COPY pom.xml /app/
COPY src /app/src
 
# Compile l'application en skippant les tests
RUN mvn clean package -DskipTests
 
# Étape 2 : Image d'exécution
FROM amazoncorretto:21
 
# Copie le jar de l'étape de build
COPY --from=build /app/target/*.jar /app/myapp.jar
 
# Expose le port 8082
EXPOSE 8082
 
# Commande pour lancer l'application
ENTRYPOINT ["java", "-jar", "/app/myapp.jar"]

