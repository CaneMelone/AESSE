package it.aesse.AESSE.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BeneDto {
    private Long id_bene;
    private String tipo;
    private String identificativo;
    private String descrizione;
}
