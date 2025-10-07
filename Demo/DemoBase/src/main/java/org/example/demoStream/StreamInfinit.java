package org.example.demoStream;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamInfinit {
    public static void main(String[] args) {

        IntStream.iterate(0,i->i+1).limit(5).forEach(System.out::println);

//        int i = 0;
//        while (i<5){
//            System.out.println(i);
//            i++;
//        }

        List<Double> value = Stream
                .generate(Math::random)
                .filter(v->(v>0.1)&&(v<0.2))
                .limit(10)
                .toList();

        value.forEach(System.out::println);

//        List<Double> value = Stream
//                .generate(Math::random)
//                .parallel()
//                .filter(v->(v>0.1)&&(v<0.2))
//                .limit(10)
//                .toList();

        Stream<UUID> streamUUID = Stream.generate(UUID::randomUUID);

        List<UUID> valueUUID = streamUUID.limit(50).toList();


    }
}
