package it.aesse.AESSE.repository;

import it.aesse.AESSE.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Long>
{
    //query per ottenere un cliente tramite id
    Cliente getClienteById(Long idCliente);
}