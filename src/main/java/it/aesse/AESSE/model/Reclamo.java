package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import it.aesse.AESSE.sub.Stato;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reclamo")
public class Reclamo
{
    //identificativo del reclamo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Long id_reclamo;

    //data in cui è stato effettuato il reclamo
    @Column(name = "data")
    private LocalDate data;

    //motivo del reclamo
    @Column(name = "motivo")
    private String motivo;

    //stato del reclamo
    @Enumerated(EnumType.STRING)
    @Column(name = "stato")
    private Stato stato;

    //Polizza a cui si riferisce il reclamo
    //una polizza può avere più reclami associati
    @ManyToOne
    @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
    private Polizza polizza;
}