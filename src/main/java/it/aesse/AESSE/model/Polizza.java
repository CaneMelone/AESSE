package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
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

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_bene", referencedColumnName = "id_bene")
    private Bene bene;


    }
