package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="Polizza")
public class Polizza {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_polizza")
        private Integer id_polizza;

        @Column(name = "data_inizio")
        private String data_inizio;

        @Column(name = "data_scadenza")
        private String data_scadenza;

        @Column(name = "tipo")
        private String tipo;

        @Column(name = "importo_rata")
        private int importo_rata;

        @Column(name = "importo")
        private int importo;

        @Column(name = "premio")
        private int premio;

        @Column(name = "stato")
        private String stato;

        @Column(name = "id_cliente")
        private Long id_cliente;

        @Column(name = "id_bene")
        private Long id_bene;
}
