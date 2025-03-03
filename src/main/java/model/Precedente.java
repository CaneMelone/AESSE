package model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Precedente {
    private Long id_precedente;
    private String tipo;
    private String pena;
    private boolean scontata;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;
}
