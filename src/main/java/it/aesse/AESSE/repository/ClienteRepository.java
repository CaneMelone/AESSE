package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {
}
