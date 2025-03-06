package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Bene;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.sub.PolizzaBene;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolizzaRepository extends JpaRepository<Polizza,Long> {
    List<Polizza> findByClienteId(Long idCliente);

    @Query("select p from Polizza p where p.cliente.id = ?1")
    List<PolizzaBene> findByCliente_Id(Long id);

    @Query("SELECT COUNT(p) FROM Polizza p WHERE p.bene.id = :beneId")
    int countByBeneId(Long beneId);

}


