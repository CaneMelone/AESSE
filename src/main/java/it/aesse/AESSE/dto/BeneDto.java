package it.aesse.AESSE.dto;

import lombok.*;

@Data
public class BeneDto
{
    //identificativo del bene
    private Long id_bene;
    //tipo di bene(auto, casa, persone..)
    private String tipo;
    //identificativo del bene(targa, codice fiscale...)
    private String identificativo;
    //una descrizione del bene
    private String descrizione;
}