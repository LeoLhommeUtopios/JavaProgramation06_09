package org.example.demoTestUnitaire.Demo2;

import java.util.Random;

public class De implements IDe{
    @Override
    public int lancer() {
        Random random = new Random();
        return random.nextInt(20);
    }
}
