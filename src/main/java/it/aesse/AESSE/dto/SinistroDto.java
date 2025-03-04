package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class SinistroDto {
    private Long id_sinistro;
    private String data;
    private String descrizione;
    private String stato;
    private BigDecimal valoreDanno;
    private BigDecimal importoConcesso;

    private PolizzaDto polizza;
}
