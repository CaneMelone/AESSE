package it.aesse.AESSE.service;

         import it.aesse.AESSE.converter.Converter;
         import it.aesse.AESSE.dto.PrecedenteDto;
         import it.aesse.AESSE.model.Precedente;
         import it.aesse.AESSE.repository.PrecedenteRepository;
         import lombok.extern.slf4j.Slf4j;
         import org.springframework.beans.factory.annotation.Autowired;
         import org.springframework.stereotype.Service;

         import java.util.Optional;

         @Slf4j
         @Service
         public class PrecedenteService extends AbstractService<Precedente, PrecedenteDto> {

             @Autowired
             private PrecedenteRepository precedenteRepository;

             @Autowired
             private Converter<Precedente, PrecedenteDto> converter;

             /**
              * Recupera una lista di oggetti PrecedenteDto per il cliente specificato e verifica se il cliente ha una pena e in che stato si trova.
              *
              * @param clienteId l'ID del cliente per filtrare gli oggetti PrecedenteDto
              * @return un oggetto PrecedenteDto con le informazioni sui precedenti e sulla pena e il suo stato
              */
             public Optional<PrecedenteDto> getPrecedentiEVerificaPena(Long clienteId) {
                 log.info("Recupero dei precedenti e verifica della pena per il cliente con ID: {}", clienteId);
                 Optional<Precedente> precedente = precedenteRepository.findByClienteId(clienteId);
                 return precedente.map(converter::toDTO);
             }
         }