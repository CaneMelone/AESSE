package it.aesse.AESSE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SinistroDto {
    private Long id_sinistro;
    private String data;
    private String tipo;
    private String descrizione;
    private String stato;
    private Long id_polizza;
    private Long id_cliente;
    private Long id_bene;
    private Long id_reclamo;
    private Long id_precedente;
}
