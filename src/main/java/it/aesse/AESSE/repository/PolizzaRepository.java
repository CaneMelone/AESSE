package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Polizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PolizzaRepository extends JpaRepository<Polizza,Long> {
    List<Polizza> findByIdCliente(Long idCliente);
}
