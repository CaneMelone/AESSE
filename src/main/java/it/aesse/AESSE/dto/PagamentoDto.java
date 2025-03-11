package it.aesse.AESSE.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PagamentoDto
{
    //identificativo del pagamento
    private Long id_pagamento;
    //data in cui si effettua il pagamento
    private LocalDate data_pagamento;
    //importo del pagamento
    private BigDecimal importo;
    //metodo di pagamento
    private String metodo;
    //causale del pagamento
    private String causale;

    //Polizza a cui si riferisce il pagamento
    private PolizzaDto polizza;
}