package it.aesse.AESSE.dto;

import lombok.*;

@Data
public class PrecedenteDto {
    private Long id_precedente;
    private String tipo;
    private String pena;
    private Boolean scontata;
    private ClienteDto cliente;
}
