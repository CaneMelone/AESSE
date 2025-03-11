package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class PolizzaDto
{
    //identificativo della polizza
    private Long id_polizza;
    //data di inizio della polizza
    private LocalDate data_inizio;
    //data di scadenza della polizza
    private LocalDate data_scadenza;
    //tipo di polizza
    private String tipo;
    //importo ratuale della polizza
    private BigDecimal importo_rata;
    //importo totale della polizza
    private BigDecimal importo;
    //premio da sottrarre al totale
    private BigDecimal premio;
    //stato della polizza
    private String stato;

    //Cliente a cui è associata la polizza
    private ClienteDto cliente;
    //Bene a cui è associata la polizza
    private BeneDto bene;
}