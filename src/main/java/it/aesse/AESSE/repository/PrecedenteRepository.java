package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Precedente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrecedenteRepository extends JpaRepository<Precedente, Long> {

    Optional<Precedente> findByClienteId(Long clienteId);

}