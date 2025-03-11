package it.aesse.AESSE.dto;

import lombok.Getter;
import lombok.Setter;

//classe che rappresenta la richiesta di cambio stato di un sinistro
@Getter
@Setter
public class StatoRequest
{
    private String stato;
}