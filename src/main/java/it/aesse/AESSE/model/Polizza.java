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
public class Polizza
{
    //identificativo della polizza
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_polizza")
    private Long id_polizza;

    //data di inizio della polizza
    @Column(name = "data_inizio")
    private LocalDate data_inizio;

    //data di scadenza della polizza
    @Column(name = "data_scadenza")
    private LocalDate data_scadenza;

    //tipo di polizza
    @Column(name = "tipo")
    private String tipo;

    //importo della rata
    @Column(name = "importo_rata")
    private BigDecimal importo_rata;

    //importo totale della polizza
    @Column(name = "importo")
    private BigDecimal importo;

    //importo del premio
    @Column(name = "premio")
    private BigDecimal premio;

    //stato della polizza
    @Column(name = "stato")
    private String stato;

    //cliente a cui è associata la polizza
    //un cliente può avere più polizze
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    //bene a cui è associata la polizza
    //un bene può avere più polizze
    @ManyToOne
    @JoinColumn(name = "id_bene", referencedColumnName = "id_bene")
    private Bene bene;
}