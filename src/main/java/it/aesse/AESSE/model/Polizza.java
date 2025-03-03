package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name ="Polizza")
public class Polizza {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id_polizza;

        private Integer costo;
        private String data_inizio;
        private String data_scadenza;
        private String tipo;
        private int importo_rata;
        private int premio;
        private String stato;



        @ManyToOne
        @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
        private List<Cliente> cliente;

        @ManyToOne
        @JoinColumn(name = "id_bene", referencedColumnName = "id_bene")
        private List<Bene> bene;

}
