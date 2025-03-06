package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.PolizzaDto;
import it.aesse.AESSE.model.Polizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class PolizzaService extends AbstractService<Polizza, PolizzaDto> {
    @Autowired
    private EmailService emailService;

    @Override
    @CacheEvict(value = "entities", allEntries = true) // Invalida la cache dopo un inserimento
    public PolizzaDto insert(PolizzaDto dto) {

        Polizza entity = converter.toEntity(dto);
        Polizza savedEntity = jpaRepository.save(entity);
        emailService.sendEmailCreazionePolizza(savedEntity.getCliente().getEmail());

        return converter.toDTO(savedEntity);
    }
}
