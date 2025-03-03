package it.aesse.AESSE.dto;

import lombok.*;

import java.util.List;

@Data
public class PolizzaDto {
    private Integer id_polizza;
    private Integer costo;
    private String data_inizio;
    private String data_scadenza;
    private String tipo;
    private int importo_rata;
    private int premio;
    private String stato;
    private List<ClienteDto> cliente;
    private List<BeneDto> bene;
}
