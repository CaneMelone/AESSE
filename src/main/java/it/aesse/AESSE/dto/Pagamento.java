package it.aesse.AESSE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    private Long id_pagamento;
    private String data;
    private double importo;
    private String metodo;
    private Long id_sinistro;
    private Long id_cliente;
    private Long id_precedente;
}
