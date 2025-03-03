package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="Polizza")
public class Polizza {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_polizza")
        private Long id_polizza;

        @Column(name = "data_inizio")
        private LocalDate data_inizio;

        @Column(name = "data_scadenza")
        private LocalDate data_scadenza;

        @Column(name = "tipo")
        private String tipo;

        @Column(name = "importo_rata")
        private BigDecimal importo_rata;

        @Column(name = "importo")
        private BigDecimal importo;

        @Column(name = "premio")
        private BigDecimal premio;

        @Column(name = "stato")
        private String stato;

        @Column(name = "id_cliente")
        private Long id_cliente;

        @Column(name = "id_bene")
        private Long id_bene;
}
