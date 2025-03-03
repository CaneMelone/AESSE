package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Polizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolizzaRepository extends JpaRepository<Polizza,Long> {
}
