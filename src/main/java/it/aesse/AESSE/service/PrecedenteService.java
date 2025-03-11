package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.PrecedenteConverter;
import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.model.Precedente;
import it.aesse.AESSE.repository.PrecedenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Classe di servizio per la gestione delle entità Precedente.
 */
@Slf4j
@Service
public class PrecedenteService extends AbstractService<Precedente, PrecedenteDto>
{
    @Autowired
    private PrecedenteRepository precedenteRepository;

    @Autowired
    private PrecedenteConverter converter;

    /**
     * Recupera una lista di PrecedenteDto per il tipo specificato.
     *
     * @param tipo il tipo dei precedenti
     * @return una lista di PrecedenteDto associati al tipo specificato
     */
    public List<PrecedenteDto> findByTipo(String tipo)
    {
        log.info("Recupero dei precedenti con tipo: {}", tipo);
        return converter.toDTOList(precedenteRepository.findByTipo(tipo));
    }

    /**
     * Recupera una lista di PrecedenteDto per l'ID del cliente specificato.
     *
     * @param clienteId l'ID del cliente
     * @return una lista di PrecedenteDto associati all'ID del cliente specificato
     */
    public List<PrecedenteDto> findByClienteId(Long clienteId)
    {
        log.info("Recupero dei precedenti per il cliente con ID: {}", clienteId);
        return converter.toDTOList(precedenteRepository.findByClienteId(clienteId));
    }
}