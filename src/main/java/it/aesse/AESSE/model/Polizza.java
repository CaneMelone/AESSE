package it.aesse.AESSE.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

        /*
        id_polizza PK
        data_inizio
        data_scadenza
        tipo
        importo_rata
        premio
        stato
        id_cliente FK
        id_bene FK
        */


        @ManyToOne
        @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
        private List<Clienti> cliente;

        @ManyToOne
        @JoinColumn(name = "id_bene", referencedColumnName = "id_bene")
        private List<Beni> bene;

}
