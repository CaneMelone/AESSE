package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Bene")
public class Bene
{
    //identificativo del bene
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bene")
    private Long id_bene;

    //tipo di bene(auto, casa, persone..)
    @Column(name = "tipo")
    private String tipo;

    //identificativo del bene(targa, codice fiscale...)
    @Column(name = "identificativo")
    private String identificativo;

    //una descrizione del bene
    @Column(name = "descrizione")
    private String descrizione;
}