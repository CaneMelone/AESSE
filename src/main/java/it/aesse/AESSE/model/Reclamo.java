package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Reclamo")
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reclamo")
    private Long id_reclamo;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "stato")
    private String stato;

    @ManyToOne
    @JoinColumn(name = "id_polizza")
    private Polizza polizza;
}