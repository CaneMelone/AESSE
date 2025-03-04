package it.aesse.AESSE.repository;

            import it.aesse.AESSE.model.Reclamo;
            import org.springframework.data.jpa.repository.JpaRepository;
            import org.springframework.data.jpa.repository.Query;
            import org.springframework.data.repository.query.Param;
            import org.springframework.stereotype.Repository;

            import java.time.LocalDate;
            import java.util.List;


            @Repository
            public interface ReclamoRepository extends JpaRepository<Reclamo, Long> {

                List<Reclamo> findByDate(String date);

                List<Reclamo> findByMotivo(String motivo);

                @Query("select r from Reclamo r where r.polizza.cliente.nome = ?1")
                List<Reclamo> findByNome(String nome);

                List<Reclamo> findByStato(String stato);
            }