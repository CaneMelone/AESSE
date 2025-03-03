package it.aesse.AESSE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReclamoDto {
    private Long id_reclamo;
    private LocalDate data;
    private String motivo;
    private String stato;

    private Long id_polizza;
}