package org.example.demoStream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> personnes = Arrays.asList(new Person("toto",12,"12334456"),
                new Person("titi",20,"3246790089"),
                new Person("tutu",22,"3247856749"),
                new Person("tata",55,"3246758689"),
                new Person("toto",12,"12334456"),
                new Person("titi",20,"3246790089"),
                new Person("tutu",22,"3247856749"),
                new Person("tata",55,"3246758689"));


        OptionalDouble moyenneAge = personnes.stream()
                .mapToInt(Person::getAge)
                .average();

        personnes.stream()
                .collect(Collectors.groupingBy(Person::getName,Collectors.counting()))
                .forEach((key,value)-> System.out.println(key + " : "+value));

//        for(Map.Entry entry : personByName.entrySet()){
//            System.out.println(entry.getKey() + " : "+entry.getValue());
//        }

        if(moyenneAge.isPresent()){
            System.out.println("Moyenne d'age : "+moyenneAge.getAsDouble() +" ans");
        }
    }
}
