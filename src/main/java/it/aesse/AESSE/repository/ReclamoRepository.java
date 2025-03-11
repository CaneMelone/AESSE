package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo, Long>
{
    // Query per recuperare i reclami in base alla data
    List<Reclamo> findByData(LocalDate date);

    // Query per recuperare i reclami in base al motivo
    List<Reclamo> findByMotivo(String motivo);

    // Query per recuperare i reclami in base al nome
    @Query("select r from Reclamo r where r.polizza.cliente.nome = ?1")
    List<Reclamo> findByNome(String nome);

    // Query per recuperare i reclami in base allo stato
    List<Reclamo> findByStato(String stato);
}