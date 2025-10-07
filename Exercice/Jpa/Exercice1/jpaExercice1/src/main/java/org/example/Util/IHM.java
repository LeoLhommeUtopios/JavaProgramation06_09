package org.example.Util;

import org.example.Entity.Animal;
import org.example.Repository.AnimalRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class IHM {
    private EntityManagerFactory emf;
    private EntityManager em;
    private Scanner sc;
    private AnimalRepository animalRepository;

    public IHM (){
        emf = Persistence.createEntityManagerFactory("Exo1_jpa");
        em = emf.createEntityManager();
        animalRepository = new AnimalRepository(em);
        sc = new Scanner(System.in);
    }


    public void start (){
        String entry;
        while(true){
            System.out.println(" ---- Zoo Menu -----");
            System.out.println("1/ create Animal");
            System.out.println("2/ get animal by id");
            System.out.println("3/ get animal by name");
            System.out.println("4/ get animal by diet");
            entry = sc.nextLine();
            switch(entry){
                case "1"-> createAnimal();
                case "2"-> getAnimalById();
                case "3"-> getAnimalByName();
                case "4"-> getAnimalByDiet();
                default -> {
                    return;
                }
            }
        }
    }

    public void createAnimal (){
        System.out.println(" --- Add Animal --- ");
        System.out.println(" animal name :");
        String name = sc.nextLine();

        System.out.println("Animal age");
        int age = sc.nextInt();
        sc.nextLine();

        Diet diet = getDiet();

        LocalDate arrivalDate =LocalDate.now();

        Animal animal = Animal.builder().name(name).age(age).diet(diet).arrivalDate(arrivalDate).build();

        animalRepository.create(animal);

        System.out.println("Animal crée" + animal);
    }

    public void getAnimalById (){
        System.out.println(" --- Get By Id --- " );
        System.out.println("id animal :");
        int id = sc.nextInt();
        sc.nextLine();

        Animal animalFound = animalRepository.findByID(id);
        if(animalFound != null){
            System.out.println(animalFound);
        }else{
            System.out.println("Animal not found");
        }
    }

    public void getAnimalByName(){
        System.out.println(" --- Get By Name --- ");
        System.out.println("Animal name :");
        String name = sc.nextLine();

        List<Animal> animals = animalRepository.findByName(name);

        if(animals.isEmpty()){
            System.out.println("No animal found");
        }else{
            System.out.println(animals);
        }
    }

    public void getAnimalByDiet (){
        System.out.println(" --- Get By Diet --- ");

        Diet diet = getDiet();

        List<Animal> animals = animalRepository.findByDiet(diet);

        if(animals.isEmpty()){
            System.out.println("No animal found");
        }else{
            System.out.println(animals);
        }
    }

    private Diet getDiet (){
        for (Diet value : Diet.values()){
            System.out.println(value);
        }
        String diet = sc.nextLine();
        return Diet.valueOf(diet.toUpperCase());
    }
}
