package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.PolizzaConverter;
import it.aesse.AESSE.dto.PolizzaDto;
import it.aesse.AESSE.model.Polizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import it.aesse.AESSE.repository.PolizzaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

//todo creare una nuova tabella con il tipo di precedente

@Service
public class PolizzaService extends AbstractService<Polizza, PolizzaDto> {

    @Autowired
    private PolizzaRepository polizzaRepository;

    @Autowired
    private PolizzaConverter polizzaConverter;

    @Transactional
    public void aggiornaStatoPolizza(Long id_polizza, String stato) {
        Polizza polizza = polizzaRepository.findById(id_polizza).get();
           //     .orElseThrow(() -> new EntityNotFoundException("Polizza con ID " + id_polizza + " non trovata."));

        polizza.setStato(stato); // Aggiorna lo stato della polizza
        polizza.setData_inizio(LocalDate.now()); // Aggiorna la data di inizio con la data odierna
        polizzaRepository.save(polizza); // Salva le modifiche nel database
    }
    @Autowired
    private EmailService emailService;

    @Override
    @CacheEvict(value = "entities", allEntries = true) // Invalida la cache dopo un inserimento
    public PolizzaDto insert(PolizzaDto dto) {

        Polizza entity = converter.toEntity(dto);
        Polizza savedEntity = jpaRepository.save(entity);
        emailService.sendEmailCreazionePolizza(savedEntity.getCliente().getEmail());

        return polizzaConverter.toDTO(savedEntity);


    }
}

