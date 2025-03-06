package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.Converter;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public abstract class AbstractService<Entity, DTO> implements ServiceDTO<DTO> {

    @Autowired
    protected JpaRepository<Entity, Long> jpaRepository;

    @Autowired
    protected Converter<Entity, DTO> converter;

    public AbstractService() {
    }

//    @Override
//    @CacheEvict(value = "entities", allEntries = true) // Invalida la cache dopo un inserimento
//    public DTO insert(DTO dto) {
//        log.info("Inserimento di un nuovo elemento: {}", dto);
//        Entity entity = converter.toEntity(dto);
//        Entity savedEntity = jpaRepository.save(entity);
//        return converter.toDTO(savedEntity);
//    }


    @Override
    @CacheEvict(value = "entities", allEntries = true)
    public DTO insert(DTO dto) {
        log.info("Inserimento di un nuovo elemento: {}", dto);
        Entity entity = converter.toEntity(dto);

        // Recupera il campo identificativo usando reflection:
        Field idField = null;
        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(jakarta.persistence.Id.class)) {
                idField = field;
                break;
            }
        }

        if (idField != null) {
            idField.setAccessible(true);
            Object idValue = ReflectionUtils.getField(idField, entity);
            // Se il valore dell'ID non è nullo, significa che stai tentando di inserire un record con ID già esistente
            if (idValue != null) {
                throw new IllegalArgumentException("Impossibile eseguire l'insert: l'ID deve essere nullo per un nuovo record");
            }
        } else {
            log.warn("Nessun campo @Id trovato nella classe: {}", entity.getClass().getSimpleName());
        }

        Entity savedEntity = jpaRepository.save(entity);
        return converter.toDTO(savedEntity);
    }


    @Override
    @Cacheable("entities") // Caching della lista per ottimizzare performance
    public List<DTO> getAll() {
        log.info("Recupero di tutti gli elementi");
        return converter.toDTOList((jpaRepository.findAll()));
    }

    @Override
    @Cacheable(value = "entities", key = "#id") // Cache singolo elemento
    public DTO read(long id) {
        log.info("Recupero dell'elemento con ID: {}", id);
        Optional<Entity> entity = jpaRepository.findById(id);
        return entity.map(converter::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Elemento con ID " + id + " non trovato"));
    }

    @Override
    @CacheEvict(value = "entities", key = "#dto.id") // Invalida la cache dopo un update
    public DTO update(DTO dto) {
        log.info("Aggiornamento dell'elemento: {}", dto);
        Entity entity = converter.toEntity(dto);
        Entity updatedEntity = jpaRepository.save(entity);
        return converter.toDTO(updatedEntity);
    }

    @Override
    @CacheEvict(value = "entities", key = "#id") // Rimuove dalla cache l'elemento eliminato
    public void delete(long id) {
        log.warn("Eliminazione dell'elemento con ID: {}", id);
        if (!jpaRepository.existsById(id)) {
            throw new EntityNotFoundException("Elemento con ID " + id + " non esistente");
        }
        jpaRepository.deleteById(id);
    }

    @Override
    @Cacheable("entities") // Cache per ottenere tutti gli elementi
    public List<DTO> readAll() {
        log.info("Lettura di tutti gli elementi");
        return converter.toDTOList((jpaRepository.findAll()));
    }

    // Nuovo metodo per paginazione
    public List<DTO> getAllPaged(Pageable pageable) {
        log.info("Recupero degli elementi con paginazione: Page {}, Size {}", pageable.getPageNumber(), pageable.getPageSize());
        return jpaRepository.findAll(pageable)
                .stream()
                .map(converter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "entities", key = "#id")
    public DTO patch(Long id, Map<String, Object> changes) {
        log.info("PATCH dell'elemento con ID: {}, changes: {}", id, changes);
        Entity entity = jpaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Elemento con ID " + id + " non trovato"));
        changes.forEach((fieldName, fieldValue) -> {
            Field field = ReflectionUtils.findField(entity.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                Object valueToSet = fieldValue;
                // Se il campo è di tipo BigDecimal, convertilo se necessario
                if (field.getType().equals(BigDecimal.class)) {
                    if (fieldValue instanceof Number) {
                        valueToSet = BigDecimal.valueOf(((Number) fieldValue).doubleValue());
                    } else if (fieldValue instanceof String) {
                        try {
                            valueToSet = new BigDecimal((String) fieldValue);
                        } catch (NumberFormatException e) {
                            log.error("Impossibile convertire {} in BigDecimal per il campo {}", fieldValue, fieldName);
                        }
                    }
                }
                // Altri controlli possono essere aggiunti qui per altri tipi (es. LocalDate)
                ReflectionUtils.setField(field, entity, valueToSet);
            } else {
                log.warn("Campo '{}' non trovato in Entity {}", fieldName, entity.getClass().getSimpleName());
            }
        });
        Entity updated = jpaRepository.save(entity);
        return converter.toDTO(updated);
    }
}



