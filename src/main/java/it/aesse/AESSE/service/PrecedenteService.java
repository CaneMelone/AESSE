// PrecedenteService.java
          package it.aesse.AESSE.service;

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
              private PrecedenteConverter converter;

              // Metodo per recuperare i precedenti in base al tipo
              public List<PrecedenteDto> findByTipo(String tipo) {
                  log.info("Recupero dei precedenti con tipo: {}", tipo);
                  return converter.toDTOList(precedenteRepository.findByTipo(tipo));
              }

              // Metodo per recuperare i precedenti in base all'ID del cliente
              public List<PrecedenteDto> findByClienteId(Long clienteId) {
                  log.info("Recupero dei precedenti per il cliente con ID: {}", clienteId);
                  return converter.toDTOList(precedenteRepository.findByClienteId(clienteId));
              }
          }