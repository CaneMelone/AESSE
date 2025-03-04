package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
public class PagamentoDto {
    private Long id_pagamento;
    private String data_pagamento;
    private BigDecimal importo;
    private String metodo;
    private String causale;
    private PolizzaDto polizza;
}
