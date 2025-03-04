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
@Table(name = "sinistro")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long id_sinistro;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "descrizione", columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "stato", length = 50)
    private String stato;

    @Column(name = "valore_danno", precision = 10, scale = 2)
    private BigDecimal valoreDanno;

    @Column(name = "importo_concesso", precision = 10, scale = 2)
    private BigDecimal importoConcesso;

    @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
    private Polizza polizza;
}