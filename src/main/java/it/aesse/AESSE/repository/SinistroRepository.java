package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SinistroRepository extends JpaRepository<Sinistro,Long> {

    @Query("SELECT s FROM Sinistro s JOIN s.polizza p WHERE p.cliente.id = :idCliente")
    List<Sinistro> findByClienteId(@Param("idCliente") Long idCliente);

    @Query("SELECT s FROM Sinistro s WHERE s.polizza.id_polizza = :polizzaId")
    List<Sinistro> findByPolizzaId(@Param("polizzaId") Long polizzaId);

    List<Sinistro> findByStato(String stato);

}
