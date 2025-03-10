// PolizzaService.java
    package it.aesse.AESSE.service;

    import it.aesse.AESSE.converter.PolizzaConverter;
    import it.aesse.AESSE.dto.PolizzaDto;
    import it.aesse.AESSE.model.Polizza;
    import it.aesse.AESSE.repository.PolizzaRepository;
    import it.aesse.AESSE.service.EmailService;
    import jakarta.persistence.EntityNotFoundException;
    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.cache.annotation.CacheEvict;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;

    @Service
    public class PolizzaService extends AbstractService<Polizza, PolizzaDto> {

        @Autowired
        private PolizzaRepository polizzaRepository;

        @Autowired
        private PolizzaConverter polizzaConverter;

        @Autowired
        private EmailService emailService;

        @Transactional
        public void aggiornaStatoPolizza(Long id_polizza, String stato) {
            Polizza polizza = polizzaRepository.findById(id_polizza)
                .orElseThrow(() -> new EntityNotFoundException("Polizza con ID " + id_polizza + " non trovata."));
            polizza.setStato(stato);
            polizza.setData_inizio(LocalDate.now());
            polizzaRepository.save(polizza);
        }

        @Override
        @CacheEvict(value = "entities", allEntries = true)
        public PolizzaDto insert(PolizzaDto dto) {
            Polizza entity = polizzaConverter.toEntity(dto);
            Polizza savedEntity = polizzaRepository.save(entity);
            emailService.sendEmailCreazionePolizza(savedEntity.getCliente().getEmail());
            return polizzaConverter.toDTO(savedEntity);
        }

        public Polizza findById(Long id) {
            return polizzaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Polizza con ID " + id + " non trovata."));
        }
    }