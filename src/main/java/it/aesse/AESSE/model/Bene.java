package it.aesse.AESSE.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_bene;
    private String tipo;
    private String identificativo;
    private String descrizione;
}