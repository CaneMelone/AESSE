package it.aesse.AESSE.dto;

import lombok.*;

@Data
public class PagamentoDto {
    private Long id_pagamento;
    private String data;
    private double importo;
    private String metodo;
    private Long id_sinistro;
    private Long id_cliente;
    private Long id_precedente;
}
