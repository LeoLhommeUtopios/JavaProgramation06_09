package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evenement")
    private long id;
    private String name;
    @Column(name = "date_evenement")
    private LocalDateTime dateEvenement;
    @Column(name = "nombre_place")
    private int nbrPlace;
    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "evenement")
    private List<Billet> billets;
}
