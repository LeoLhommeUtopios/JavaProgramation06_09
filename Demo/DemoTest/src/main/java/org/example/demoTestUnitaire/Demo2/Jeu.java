package org.example.demoTestUnitaire.Demo2;

public class Jeu {

    public final IDe de;

    public Jeu(IDe de){
        this.de = de;
    }

    public boolean jouer(){
        return de.lancer() == 15;
    }
}
