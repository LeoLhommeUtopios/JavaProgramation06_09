package org.example.entity;

import com.sun.xml.bind.v2.model.core.ID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private long id;
    private String nom;
    private String prenom;
    private int age;
    private String telephone;

    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "client")
    List<Billet> billets ;

}
