package it.aesse.AESSE.service;

                 import it.aesse.AESSE.converter.Converter;
                 import it.aesse.AESSE.converter.ReclamoConverter;
                 import it.aesse.AESSE.dto.ReclamoDto;
                 import it.aesse.AESSE.model.Reclamo;
                 import it.aesse.AESSE.repository.ReclamoRepository;
                 import lombok.extern.slf4j.Slf4j;
                 import org.springframework.beans.factory.annotation.Autowired;
                 import org.springframework.stereotype.Service;

                 import java.util.List;


                 @Slf4j
                 @Service
                 public class ReclamoService extends AbstractService<Reclamo, ReclamoDto> {

                     @Autowired
                     private ReclamoRepository reclamoRepository;

                     @Autowired
                     private ReclamoConverter reclamoConverter;


                     /*// Metodo per recuperare i reclami in base allo stato
                     public List<ReclamoDto> findByStatus(String status) {
                         log.info("Recupero dei reclami con stato: {}", status);
                         return converter.toDTOList(reclamoRepository.findByStatus(status));
                     }

                     // Metodo per recuperare i reclami in base all'ID del cliente
                     public List<ReclamoDto> findByCustomerId(Long customerId) {
                         log.info("Recupero dei reclami per il cliente con ID: {}", customerId);
                         return converter.toDTOList(reclamoRepository.findByCustomerId(customerId));
                     }*/
                 }