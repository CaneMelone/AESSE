package it.aesse.AESSE.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class ClienteDto
{
    //identificativo del cliente
    private Long id;
    //nome del cliente
    private String nome;
    //cognome del cliente
    private String cognome;
    //data di nascita del cliente
    private LocalDate data_nascita;
    // indirizzo di residenza del cliente
    private String indirizzo;
    //numero di telefono del cliente
    private String telefono;
    //email personale del cliente
    private String email;
    //codice fiscale del cliente
    private String codice_fiscale;
    //se il cliente è soggetto a legge Bersani
    private Boolean bersani;
}