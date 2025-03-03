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
public class Bene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bene")
    private Long id_bene;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "identificativo")
    private String identificativo;

    @Column(name = "descrizione")
    private String descrizione;
}