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

    //TODO un service che si chiama GetPrecedenti Si deve chiamare nel controller cosi
    // GetPrecedenti Questo metodo in base al ID del cliente
    // Mi va a richiamare il codice fiscale lindirizzo la pena e se e stata scontata

    public List<PrecedenteDto> GetPrecedenti(Long idCliente) {
        log.info("Recupero dei precedenti con idCliente: {}", idCliente);
        return precedenteConverter.toDTOList(precedenteRepository.findByIdCliente(idCliente));
    }

}