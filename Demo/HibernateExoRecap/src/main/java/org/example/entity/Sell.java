package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.utils.SellStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id_sell")
    private int id;

    @OneToMany(mappedBy = "sell",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<SellProduct> sellProducts;

    private LocalDate sellDate;

    private SellStatus sellStatus;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

}
