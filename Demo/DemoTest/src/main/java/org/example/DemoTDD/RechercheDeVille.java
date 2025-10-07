package org.example.DemoTDD;

import java.util.List;

public class RechercheDeVille {

    private List<String> villes = List.of("Paris", "Budapest", "Skopje", "Rotterdam", "Valence", "Vancouver", "Amsterdam", "Vienne", "Sydney", "New York", "Londres", "Bangkok", "Hong Kong", "Dubaï", "Rome", "Istanbul");

    public List<String> recherche (String query){
        if(query.equals("*")) return villes;
        else if(query.length()<2) throw new NotFoundException("not found");
        return villes.stream().filter(v -> v.toUpperCase().startsWith(query.toUpperCase())).toList();
    }
}
