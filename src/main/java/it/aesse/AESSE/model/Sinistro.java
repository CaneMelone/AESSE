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
@Table(name = "Sinistro")
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long idSinistro;

//    @ManyToOne
//    @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
//    private Polizza polizza;

    @Column(name = "id_polizza")
    private Long idPolizza;

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
}
