package org.example;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private int score;
    private boolean lastFrame;
    private IGenerateur generateur;
    private List<Roll> rolls;

    public Frame(IGenerateur generateur, boolean lastFrame) {
        this.lastFrame = lastFrame;
        this.generateur = generateur;
        rolls = new ArrayList<>();
    }

    public int getScore(){
        score = 0;
        rolls.forEach(s ->{
            score += s.getPins();
        });
        return score;
    }

    public void setRolls(List<Roll> rolls) {
        this.rolls = rolls;
    }

    public boolean makeRoll() {
        int max = 10;
        if(!lastFrame){
            if(rolls.isEmpty() || (rolls.size()<2 && rolls.getFirst().getPins() != 10)){
                max = rolls.isEmpty()? 10 : 10 - rolls.getFirst().getPins();
                rolls.add(new Roll(generateur.randomPin(max)));
                return true;
            }
         return false;
        }
        else{
            if(rolls.size() <= 2 && (rolls.getFirst().getPins() == 10 || (rolls.get(0).getPins() + rolls.get(1).getPins() == 10))){
                max = (rolls.size() ==2 && rolls.get(0).getPins() + rolls.get(1).getPins() != 10) ? 10 - rolls.get(1).getPins() : 10;
                rolls.add(new Roll(generateur.randomPin(max)));
                return true;
            }
            return false;
        }

    }
}
