package org.example.demoTestUnitaire;

import org.example.demoTestUnitaire.demo1.Calcul;

public class Main {
    public static void main(String[] args) {
        Calcul calcul = new Calcul();
        System.out.println(calcul.division(10,0));
    }
}
