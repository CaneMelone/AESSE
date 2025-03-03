package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long idPagamento;

//    @ManyToOne
//    @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
//    private Polizza polizza;

    @Column(name = "id_polizza")
    private Long idPolizza;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "importo", precision = 10, scale = 2)
    private BigDecimal importo;

    @Column(name = "metodo", length = 50)
    private String metodo;

    @Column(name = "causale", length = 100)
    private String causale;
}
