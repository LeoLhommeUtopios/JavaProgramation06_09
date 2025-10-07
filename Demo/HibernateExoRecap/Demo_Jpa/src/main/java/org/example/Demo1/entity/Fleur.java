package org.example.Demo1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fleur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_fleure")
    private int id;
    private String color;

    @ManyToOne()
    @JoinColumn(name = "id_plante")
    private Plante plante;

}
