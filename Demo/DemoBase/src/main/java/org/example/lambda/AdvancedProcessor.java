package org.example.lambda;

@FunctionalInterface
public interface AdvancedProcessor {

    String process (String input);

    default void print(String input){
        System.out.println("Traitement de : "+input);
    }

    static void info(){
        System.out.println("Interface fonctionelle personalisée pour le traitement de String");
    }
}
