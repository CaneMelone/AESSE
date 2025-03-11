package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Bene;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneRepository extends JpaRepository<Bene,Long> {}