package it.aesse.AESSE.dto;

import it.aesse.AESSE.sub.Stato;
import lombok.*;

import java.time.LocalDate;

@Data
public class ReclamoDto {
    private Long id_reclamo;
    private LocalDate data;
    private String motivo;
    private Enum <Stato> stato;

    private PolizzaDto polizza;
}