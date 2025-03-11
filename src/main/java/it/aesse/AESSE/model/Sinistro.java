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
public class Sinistro
{
    //identificativo del sinistro
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sinistro")
    private Long id_sinistro;

    //data in cui si verifica il sinistro
    @Column(name = "data")
    private LocalDate data;

    //descrizione del sinistro
    @Column(name = "descrizione", columnDefinition = "TEXT")
    private String descrizione;

    //stato del sinistro
    @Column(name = "stato", length = 50)
    private String stato;

    //valore del danno
    @Column(name = "valore_danno", precision = 10, scale = 2)
    private BigDecimal valoreDanno;

    //importo concesso
    @Column(name = "importo_concesso", precision = 10, scale = 2)
    private BigDecimal importoConcesso;

    //Polizza a cui è associato il sinistro
    //una polizza può avere più sinistri associati
    @ManyToOne
    @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
    private Polizza polizza;
}