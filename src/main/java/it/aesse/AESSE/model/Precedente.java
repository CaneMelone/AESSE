package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Precedente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precedente")
    private Long id_precedente;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "pena")
    private String pena;

    @Column(name = "scontata")
    private Boolean scontata;

    @Column(name = "id_cliente")
    private Long id_cliente;
}
