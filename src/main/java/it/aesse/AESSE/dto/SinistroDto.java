package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class SinistroDto
{
    //identificativo del sinistro
    private Long id_sinistro;
    //data del sinistro
    private LocalDate data;
    //descrizione del sinistro
    private String descrizione;
    //stato del sinistro
    private String stato;
    //valore del danno
    private BigDecimal valoreDanno;
    //importo concesso
    private BigDecimal importoConcesso;

    //Polizza associata al sinistro
    private PolizzaDto polizza;
}