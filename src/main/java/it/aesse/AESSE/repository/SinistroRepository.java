package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Sinistro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SinistroRepository extends JpaRepository<Sinistro,Long> {
}
