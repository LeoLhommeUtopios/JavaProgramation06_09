package org.example.demoaspect.service;

import org.example.demoaspect.annotation.Performance;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    @Performance
    public void methode (){
        System.out.println("ma logique metier");
    }
    @Performance
    public String methodWithResult(){
        return "result";
    }

    public void methodThrow (){
        throw new RuntimeException("execption throws");
    }
}
