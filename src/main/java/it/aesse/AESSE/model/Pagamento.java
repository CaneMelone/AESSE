package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Pagamento")
public class Pagamento {

    //identificativo del pagamento
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Long id_pagamento;

    //data del pagamento
    @Column(name = "data_pagamento")
    private LocalDate data_pagamento;

    //importo del pagamento
    @Column(name = "importo", precision = 10, scale = 2)
    private BigDecimal importo;

    //metodo di pagamento
    @Column(name = "metodo", length = 50)
    private String metodo;

    //causale del pagamento
    @Column(name = "causale", length = 100)
    private String causale;

    //Polizza a cui è associato il pagamento
    //una polizza può avere più pagamenti
    @ManyToOne
    @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
    private Polizza polizza;
}