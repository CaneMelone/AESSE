package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Reclamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReclamoRepository extends JpaRepository<Reclamo,Long> {
}
