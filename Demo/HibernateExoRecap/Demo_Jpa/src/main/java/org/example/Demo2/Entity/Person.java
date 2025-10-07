package org.example.Demo2.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Data

//TABLE_PER_CLASS crée une table par classe contenant dans chacune toute les information de la classe mere
// et de la classe fille

//SINGLE_TABLE : Crée une seule table contenant toute les colone de la classe mere et des classes filles
// en mettant a null les valeurs des colones des autre classe fille

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int age;

}
