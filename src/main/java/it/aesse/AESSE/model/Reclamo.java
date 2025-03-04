package it.aesse.AESSE.model;

        import jakarta.persistence.*;
        import lombok.AllArgsConstructor;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;
        import it.aesse.AESSE.sub.Stato;

        import java.time.LocalDate;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        @Entity
        @Table(name = "Reclamo")
        public class Reclamo {
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "id_reclamo")
            private Long id_reclamo;

            @Column(name = "data")
            private LocalDate data;

            @Column(name = "motivo")
            private String motivo;

            @Enumerated(EnumType.STRING)
            @Column(name = "stato")
            private Stato stato;

            @ManyToOne
            @JoinColumn(name = "id_polizza", referencedColumnName = "id_polizza")
            private Polizza polizza;
        }