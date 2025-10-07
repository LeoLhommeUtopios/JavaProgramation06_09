package org.example.lambda;

import org.w3c.dom.ls.LSOutput;

public class AdvancedProcessorDemo {

    public static void main(String[] args) {
        AdvancedProcessor processor = input -> input.trim().toUpperCase();

        System.out.println("Resultat : "+processor.process("Hello world"));

        processor.print("Hello world");

        AdvancedProcessor.info();
    }

}
