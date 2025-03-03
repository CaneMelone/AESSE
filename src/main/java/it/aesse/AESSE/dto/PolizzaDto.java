package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class PolizzaDto {
    private Long id_polizza;
    private LocalDate data_inizio;
    private LocalDate data_scadenza;
    private String tipo;
    private BigDecimal importo_rata;
    private BigDecimal importo;
    private BigDecimal premio;
    private String stato;
    private Long id_cliente;
    private Long id_bene;
}
