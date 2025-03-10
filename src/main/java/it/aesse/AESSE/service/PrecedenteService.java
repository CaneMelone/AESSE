package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.Converter;
import it.aesse.AESSE.converter.PrecedenteConverter;
import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.model.Precedente;
import it.aesse.AESSE.repository.PrecedenteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PrecedenteService extends AbstractService<Precedente, PrecedenteDto> {

    @Autowired
    private PrecedenteRepository precedenteRepository;

    @Autowired
    private PrecedenteConverter precedenteConverter;

    // Metodo per recuperare i precedenti in base alla descrizione
    public List<PrecedenteDto> findByDescription(String description) {
        log.info("Recupero dei precedenti con descrizione: {}", description);
        return converter.toDTOList(precedenteRepository.findByDescription(description));
    }

    // Metodo per recuperare i precedenti in base all'ID del caso
    public List<PrecedenteDto> findByCaseId(Long caseId) {
        log.info("Recupero dei precedenti per il caso con ID: {}", caseId);
        return converter.toDTOList(precedenteRepository.findByCaseId(caseId));
    }
}