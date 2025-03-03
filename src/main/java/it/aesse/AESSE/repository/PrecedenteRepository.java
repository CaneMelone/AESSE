package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Precedente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrecedenteRepository extends JpaRepository<Precedente,Long> {
    Iterable<Precedente> findByDescription(String description);

    Iterable<Precedente> findByCaseId(Long caseId);
}
