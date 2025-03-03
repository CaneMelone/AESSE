package it.aesse.AESSE.service;

                 import it.aesse.AESSE.converter.Converter;
                 import it.aesse.AESSE.dto.ReclamoDto;
                 import it.aesse.AESSE.model.Reclamo;
                 import it.aesse.AESSE.repository.ReclamoRepository;
                 import jakarta.persistence.EntityNotFoundException;
                 import lombok.extern.slf4j.Slf4j;
                 import org.springframework.beans.factory.annotation.Autowired;
                 import org.springframework.cache.annotation.CacheEvict;
                 import org.springframework.cache.annotation.Cacheable;
                 import org.springframework.data.domain.Pageable;
                 import org.springframework.stereotype.Service;

                 import java.util.List;
                 import java.util.Optional;
                 import java.util.stream.Collectors;

                 @Slf4j
                 @Service
                 public class ReclamoService extends AbstractService<Reclamo, ReclamoDto> {

                     @Autowired
                     private ReclamoRepository reclamoRepository;

                     @Autowired
                     private Converter<Reclamo, ReclamoDto> converter;


                     // Metodo per recuperare i reclami in base allo stato
                     public List<ReclamoDto> findByStatus(String status) {
                         log.info("Recupero dei reclami con stato: {}", status);
                         return converter.toDTOList(reclamoRepository.findByStatus(status));
                     }

                     // Metodo per recuperare i reclami in base all'ID del cliente
                     public List<ReclamoDto> findByCustomerId(Long customerId) {
                         log.info("Recupero dei reclami per il cliente con ID: {}", customerId);
                         return converter.toDTOList(reclamoRepository.findByCustomerId(customerId));
                     }
                 }