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
@Table(name = "Cliente")
public class Cliente
{
    //identificativo del cliente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "id_cliente")
    private Long id;

    //nome del cliente
    @Column(name = "nome")
    private String nome;

    //cognome del cliente
    @Column(name = "cognome")
    private String cognome;

    //data di nascita del cliente
    @Column(name = "data_nascita")
    private LocalDate data_nascita;

    // indirizzo di residenza del cliente
    @Column(name = "indirizzo")
    private String indirizzo;

    //numero di telefono del cliente
    @Column(name = "telefono")
    private String telefono;

    //email personale del cliente
    @Column(name = "email")
    private String email;

    //codice fiscale del cliente
    @Column(name = "codice_fiscale")
    private String codice_fiscale;

    //se il cliente è soggetto a legge Bersani
    @Column(name = "bersani")
    private Boolean bersani;
}