package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Precedente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrecedenteRepository extends JpaRepository<Precedente,Long>
{
    //query per la ricerca di un precedente in base al tipo
    List<Precedente> findByTipo(String tipo);

    //query per la ricerca di un precedente in base all'ID del cliente
    List<Precedente> findByClienteId(Long clienteId);
}