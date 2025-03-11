package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Precedente")
public class Precedente
{
    //identificativo del precedente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_precedente")
    private Long id_precedente;

    //tipo di precedente
    @Column(name = "tipo")
    private String tipo;

    //pena associata
    @Column(name = "pena")
    private String pena;

    //se la pena è stata scontata
    @Column(name = "scontata")
    private Boolean scontata;

    //cliente associato al precedente
    //un cliente può avere più precedenti
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;
}
