package org.example.demoaspect.controller;

import org.example.demoaspect.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

     @Autowired
     private MainService mainService;


    @GetMapping
    public void useMethod(){
        mainService.methode();
    }

    @GetMapping("return")
    public void returning(){
        mainService.methodWithResult();
    }

    @GetMapping("throws")
    public void throwsReturn(){
        mainService.methodThrow();
    }

    @ExceptionHandler
    public String exeption (Exception e){
        return  e.getMessage();

    }
}
