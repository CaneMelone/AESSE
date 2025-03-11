package it.aesse.AESSE.dto;

import lombok.*;

@Data
public class PrecedenteDto
{
    //identificativo del precedente
    private Long id_precedente;
    //tipo di precedente
    private String tipo;
    //pena associata
    private String pena;
    //se la pena è stata scontata
    private Boolean scontata;

    //cliente associato al precedente
    private ClienteDto cliente;
}