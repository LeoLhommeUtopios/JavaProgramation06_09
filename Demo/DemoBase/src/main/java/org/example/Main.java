package org.example;

import java.util.*;
import java.util.stream.IntStream;


public class Main {
    public static void main(String[] args) {
        test();

    }


    public static void demoSwitch(){
        Scanner scanner = new Scanner(System.in);
        String entry = scanner.nextLine();
        switch(entry){
            case "1" -> System.out.println("func 1");
            case "2" -> System.out.println("func 1") ;
            case "3" -> System.out.println("func 1");
            case "4","5" -> System.out.println("func 4 5");
            default -> {
                return;
            }
        }
    }


    public static void demoMap (){
        Map<String,Integer> map = new HashMap<>();

        map.put("Java",25);
        map.put("DotNet",12);
        map.put("pyhton",3);

//        Set<Map.Entry<String,Integer>> setMap = map.entrySet();

        for (Map.Entry<String,Integer> entry : map.entrySet()){
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }

    public static void demoInstanceOf (){
        Object o = "string";

        if(o instanceof String string ){
            System.out.println(string);
        }
    }

    public static void test (){
        IntStream.iterate(0, i -> i + 1).limit(5).forEach(System.out::println);
    }




}
