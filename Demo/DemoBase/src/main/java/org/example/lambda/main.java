package org.example.lambda;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class main {

    public static void main(String[] args) {


        // Function

        Function<String,Integer> stringLengthFunc = str -> str.length();

        String input = "Bonjour";
        int length = stringLengthFunc.apply(input);

        System.out.println("Longueur de : "+input+" : "+length);

        // Consumer
        Consumer<String> printMessage = message -> System.out.println("message : "+message );
        printMessage.accept("Ceci est un message !");

        //predicate
        Predicate<Integer> isEven = number -> number%2 == 0 ;

        if(isEven.test(3)){
            System.out.println("3 est pair");
        }else{
            System.out.println("3 est impaire");
        }

        //supplier
        Supplier<Integer> ramdomSupplier = ()-> new Random().nextInt(100);
        System.out.println("nombre aleatoire : "+ramdomSupplier.get());

        List<String> liste = List.of("Pomme","Banane","Ananas");

        liste.forEach(printMessage);



    }
}
