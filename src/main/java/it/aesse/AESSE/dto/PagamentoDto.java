package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoDto {
    private Long id_pagamento;
    private LocalDate data_pagamento;
    private BigDecimal importo;
    private String metodo;
    private String causale;
    private PolizzaDto polizza;
}
