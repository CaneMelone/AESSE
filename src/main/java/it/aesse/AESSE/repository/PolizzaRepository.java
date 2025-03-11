package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.sub.PolizzaBene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolizzaRepository extends JpaRepository<Polizza,Long>
{
    //query per recuperare le polizze in base all'ID del cliente
    List<Polizza> findByClienteId(Long idCliente);

    //query per recuperare le polizze in base all'ID del cliente
    @Query("select p from Polizza p where p.cliente.id = ?1")
    List<PolizzaBene> findByCliente_Id(Long id);

    //query per contare le polizze in base all'ID del bene
    @Query("SELECT COUNT(p) FROM Polizza p WHERE p.bene.id = :beneId")
    int countByBeneId(Long beneId);
}