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
@Table(name = "Sinistro")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long id_sinistro;

    @Column(name = "id_polizza")
    private Polizza polizza;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "descrizione", columnDefinition = "TEXT")
    private String descrizione;

    @Column(name = "stato", length = 50)
    private String stato;

    @Column(name = "valore_danno", precision = 10, scale = 2)
    private BigDecimal valore_danno;

    @Column(name = "importo_concesso", precision = 10, scale = 2)
    private BigDecimal importoConcesso;
}
