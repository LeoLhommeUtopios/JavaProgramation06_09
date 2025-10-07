package org.example.lambda;

public class StringProcessorDemo {

    public static void main(String[] args) {
        StringProcessor toUpperCase = string -> string.toUpperCase();

        System.out.println("Majuscules : " + toUpperCase.process("minuscule"));
    }

    public void stringMethode (String string, StringProcessor stringprocessor){
        stringprocessor.process(string);
    }
}
