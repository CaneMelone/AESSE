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
public class ClienteDto {
    private Long id_cliente;
    private String nome;
    private String cognome;
    private LocalDate data_nascita;
    private String indirizzo;
    private String telefono;
    private String email;
    private String codice_fiscale;
    private Boolean bersani;
}
