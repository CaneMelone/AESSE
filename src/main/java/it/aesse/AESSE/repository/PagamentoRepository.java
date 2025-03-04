package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento,Long> {

    List<Pagamento> findByMetodo(String metodo);

    @Query("SELECT p FROM Pagamento p WHERE p.polizza.id_polizza = :polizzaId")
    List<Pagamento> findByPolizzaId(@Param("polizzaId") Long polizzaId);
}
