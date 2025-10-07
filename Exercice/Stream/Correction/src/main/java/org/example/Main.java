package org.example;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String csvFile = "src/main/resources/books_dataset.csv";

        List<Book> books;

        try(BufferedReader br = new BufferedReader(new FileReader(csvFile))){
            books = br.lines()
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(value -> new Book(value[0],value[1],value[2], LocalDate.parse(value[3]), Integer.parseInt(value[4]),Boolean.parseBoolean(value[5]), Double.parseDouble(value[6])))
                    .toList();

        }catch (FileNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }

        //1
//        books.stream()
//                .filter(Book::isEstDisponible)
//                .forEach(System.out::println);

        //2
//        books.stream()
//                .filter(book -> book.getDatePublication().isBefore(LocalDate.of(1950,1,1)))
//                .forEach(book -> System.out.println("titre : "+book.getTitre()+", auteur : "+book.getAuteur()));

        //3
//        books.stream()
//                .collect(Collectors.groupingBy(Book::getGenre))
//                .forEach((k,v)->{
//                    System.out.println(k + " : ");
//                    v.forEach(System.out::println);
//                });

        //4
//        books.stream()
//                .min(Comparator.comparing(Book::getDatePublication))
//                .ifPresent(System.out::println);

        //5
//        boolean result =books.stream()
//                .anyMatch(book -> book.getTitre().startsWith("Harry"));
//
//        System.out.println(result? "le livre existe" : "le livre n'existe pas");

        //6
//        OptionalDouble moyennePrix = books.stream()
//                .filter(Book::isEstDisponible)
//                .mapToDouble(Book::getPrix)
//                .average();
//
//        if(moyennePrix.isPresent()){
//            System.out.println("Prix moyenne des livres disponibles : "+moyennePrix.getAsDouble());
//        }

        //7
//        books.stream()
//                .sorted(Comparator.comparingInt(Book::getNbPages).thenComparingDouble(Book::getPrix))
//                .forEach(System.out::println);

        //8

//        int nbrPages = books.stream()
//                .filter(livre -> livre.getGenre().equals("Fantasy"))
//                .mapToInt(Book::getNbPages)
//                .sum();
//
//        System.out.println("Le nombre de page total des livres de fantasy est de : "+nbrPages);

        //9
//        books.stream()
//                .filter(Book::isEstDisponible)
//                .max(Comparator.comparingDouble(Book::getPrix))
//                .ifPresent(System.out::println);

        //10

//        books.stream()
//                .collect(Collectors.groupingBy(Book::getAuteur,Collectors.counting()))
//                .entrySet().stream()
//                .filter(entry -> entry.getValue()>17)
//                .forEach(entry -> System.out.println(entry.getKey()+" : "+entry.getValue() +" Livres"));

        //11
//        books.stream()
//                .collect(Collectors.groupingBy(Book::getGenre,Collectors.counting()))
//                .forEach((k,v)-> System.out.println(k + " : "+v));

        //12
//        books.stream()
//                .filter(book -> book.isEstDisponible() && book.getPrix()<15)
//                .forEach(System.out::println);

        //13
        books.stream()
                .collect(Collectors.groupingBy(book -> book.getDatePublication().getYear(),Collectors.summingInt(Book::getNbPages)))
                .forEach((k,v)-> System.out.println(k + " : "+v+" pages"));
    }

}