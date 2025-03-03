package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long id_pagamento;

    @Column(name = "data_pagamento")
    private LocalDate data_agamento;

    @Column(name = "importo", precision = 10, scale = 2)
    private BigDecimal importo;

    @Column(name = "metodo", length = 50)
    private String metodo;

    @Column(name = "causale", length = 100)
    private String causale;

    @Column(name = "id_polizza")
    private Long id_polizza;
}
