package org.example.Demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
@AllArgsConstructor
@Builder
@Data
@Table(name = "plante")
public class Plante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plante")
    private int id;
    @Column(nullable = false)
    private String name;
    private String color;

//    @OneToOne
//    @JoinColumn(name = "id_pot")
//    private Pot pot;

    @Embedded
    private Pot pot;

    @OneToMany(mappedBy = "plante")
    private List<Fleur> fleurs;

    @ManyToMany(mappedBy = "plantes")
    private List<Arrosoir> arrosoirs;

    public Plante() {
        fleurs = new ArrayList<>();
        arrosoirs =new ArrayList<>();
    }

    public void addFLeur (Fleur fleur){
        fleurs.add(fleur);
    }

    public void addArrosoir (Arrosoir arrosoir){
        arrosoirs.add(arrosoir);
    }
}
