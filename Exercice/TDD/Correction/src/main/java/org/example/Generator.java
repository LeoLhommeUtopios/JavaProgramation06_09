package org.example;

import java.util.Random;

public class Generator implements IGenerateur{
    @Override
    public int randomPin(int max) {
        return new Random().nextInt(max);
    }
}
