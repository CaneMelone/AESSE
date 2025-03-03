package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Reclamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id_reclamo;
    private LocalDate data;
    private String motivo;
    private String stato;

    @ManyToOne
    @JoinColumn(name = "id_polizza")
    private Polizza polizza;
}