// PrecedenteRepository.java
package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Precedente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PrecedenteRepository extends JpaRepository<Precedente, Long> {
    List<Precedente> findByClienteId(Long clienteId);
}