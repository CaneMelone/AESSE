package it.aesse.AESSE.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class ReclamoDto {
    private Long id_reclamo;
    private LocalDate data;
    private String motivo;
    private String stato;

    private Long id_polizza;
}