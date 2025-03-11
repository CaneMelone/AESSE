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

            /**
             * Classe di servizio per la gestione delle entità Polizza.
             */
            @Service
            public class PolizzaService extends AbstractService<Polizza, PolizzaDto> {

                @Autowired
                private PolizzaRepository polizzaRepository;

                @Autowired
                private PolizzaConverter polizzaConverter;

                @Autowired
                private EmailService emailService;

                /**
                 * Aggiorna lo stato di una entità Polizza con l'ID specificato.
                 *
                 * @param id_polizza l'ID della Polizza da aggiornare
                 * @param stato il nuovo stato da impostare
                 * @throws EntityNotFoundException se la Polizza con l'ID specificato non viene trovata
                 */
                @Transactional
                public void aggiornaStatoPolizza(Long id_polizza, String stato) {
                    Polizza polizza = polizzaRepository.findById(id_polizza)
                        .orElseThrow(() -> new EntityNotFoundException("Polizza con ID " + id_polizza + " non trovata."));
                    polizza.setStato(stato);
                    polizza.setData_inizio(LocalDate.now());
                    polizzaRepository.save(polizza);
                }

                /**
                 * Inserisce una nuova entità Polizza e invia un'email di creazione.
                 *
                 * @param dto il PolizzaDto da inserire
                 * @return il PolizzaDto inserito
                 */
                @Override
                @CacheEvict(value = "entities", allEntries = true)
                public PolizzaDto insert(PolizzaDto dto) {
                    Polizza entity = polizzaConverter.toEntity(dto);
                    Polizza savedEntity = polizzaRepository.save(entity);
                    emailService.sendEmailCreazionePolizza(savedEntity.getCliente().getEmail());
                    return polizzaConverter.toDTO(savedEntity);
                }

                /**
                 * Trova una entità Polizza tramite il suo ID.
                 *
                 * @param id l'ID della Polizza da trovare
                 * @return la entità Polizza trovata
                 * @throws EntityNotFoundException se la Polizza con l'ID specificato non viene trovata
                 */
                public Polizza findById(Long id) {
                    return polizzaRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Polizza con ID " + id + " non trovata."));
                }
            }