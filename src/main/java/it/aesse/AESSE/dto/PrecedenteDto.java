package it.aesse.AESSE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrecedenteDto {
    private Long id_precedente;
    private String tipo;
    private String pena;
    private boolean scontata;
    private Long id_cliente;

}
