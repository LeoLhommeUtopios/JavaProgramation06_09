package org.example.lambda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataTransformerDemo {
    public static void main(String[] args) {

        DataTransformer<Integer> doubleValue = num -> num*2;
        System.out.println("Double de 5 est : "+ doubleValue.transform(5));

        DataTransformer<String> addPrefix = str -> "Prefix_"+str;
        System.out.println("Avec un prefix : "+ addPrefix.transform("test"));

        DataTransformer<List<Integer>> sortList = list ->{
            Collections.sort(list);
            return list;
        };

        List<Integer> numbers = Arrays.asList(4,5,6,7);
        System.out.println("List triée :"+ sortList.transform(numbers));


    }
}
