package org.example.Demo1.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@Data
@Builder
@AllArgsConstructor
public class Arrosoir {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrosoir")
    private int id;
    private int contenance;

    @ManyToMany
    @JoinTable(name = "arrosoir_plante",
            joinColumns = @JoinColumn(name = "id_arrosoir"),
            inverseJoinColumns = @JoinColumn(name = "id_plante"))
    private List<Plante> plantes;

    public Arrosoir() {
        plantes = new ArrayList<>();
    }

    public void addPlante(Plante plante){
        plantes.add(plante);
    }
}
