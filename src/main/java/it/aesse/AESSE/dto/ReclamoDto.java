package it.aesse.AESSE.dto;

import it.aesse.AESSE.sub.Stato;
import lombok.*;

import java.time.LocalDate;

@Data
public class ReclamoDto
{
    //identificativo del reclamo
    private Long id_reclamo;
    //data di apertura del reclamo
    private LocalDate data;
    //motivo del reclamo
    private String motivo;
    //stato del reclamo
    private Enum <Stato> stato;

    //Polizza associata al reclamo
    private PolizzaDto polizza;
}