package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.Converter;
import it.aesse.AESSE.converter.ReclamoConverter;
import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.model.Reclamo;
import it.aesse.AESSE.repository.ReclamoRepository;
import it.aesse.AESSE.sub.Stato;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class ReclamoService extends AbstractService<Reclamo, ReclamoDto> {

    @Autowired
    private ReclamoRepository reclamoRepository;

    @Autowired
    private ReclamoConverter reclamoConverter;

    @Autowired
    private Converter<Reclamo, ReclamoDto> converter;

         /**
         * Recupera una lista di oggetti ReclamoDto per la data specificata.
         *
         * @param date la data per filtrare gli oggetti ReclamoDto
         * @return una lista di oggetti ReclamoDto che corrispondono alla data specificata
         */
        public List<ReclamoDto> findByDate(LocalDate date) {
            log.info("Recupero dei reclami con data: {}", date);
            return converter.toDTOList(reclamoRepository.findByData(date));
        }

        /**
         * Recupera una lista di oggetti ReclamoDto per il motivo specificato.
         *
         * @param motivo il motivo per filtrare gli oggetti ReclamoDto
         * @return una lista di oggetti ReclamoDto che corrispondono al motivo specificato
         */
        public List<ReclamoDto> findByMotivo(String motivo) {
            log.info("Recupero dei reclami con motivo: {}", motivo);
            return converter.toDTOList(reclamoRepository.findByMotivo(motivo));
        }

        /**
         * Recupera una lista di oggetti ReclamoDto per il nome specificato.
         *
         * @param nome il nome per filtrare gli oggetti ReclamoDto
         * @return una lista di oggetti ReclamoDto che corrispondono al nome specificato
         */
        public List<ReclamoDto> findByNome(String nome) {
            log.info("Recupero dei reclami con nome: {}", nome);
            return converter.toDTOList(reclamoRepository.findByNome(nome));
        }

        /**
         * Recupera una lista di oggetti ReclamoDto per lo stato specificato.
         *
         * @param stato lo stato per filtrare gli oggetti ReclamoDto
         * @return una lista di oggetti ReclamoDto che corrispondono allo stato specificato
         */
        public List<ReclamoDto> findByStato(String stato) {
            log.info("Recupero dei reclami con stato: {}", stato);
            return converter.toDTOList(reclamoRepository.findByStato(stato));
        }



            public List<ReclamoDto> findByCriteria(String valore, String controllo) {
                log.info("Recupero dei reclami con criteri - Valore: {}, Controllo: {}", valore, controllo);
                List<Reclamo> reclami;

                if (controllo.equals("nome")) {
                    reclami = reclamoRepository.findByNome(valore);
                } else if (controllo.equals("data")) {
                    LocalDate date = LocalDate.parse(valore, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    reclami = reclamoRepository.findByData(date);
                } else if (controllo.equals("motivo")) {
                    reclami = reclamoRepository.findByMotivo(valore);
                } else if (controllo.equals("stato")) {
                    reclami = reclamoRepository.findByStato(valore);
                } else {
                    reclami = reclamoRepository.findAll();
                }

                return converter.toDTOList(reclami);
            }
//TODO la possibiita di cambiare lo stato al reclamo

            /**
             * Cambia lo stato di un reclamo identificato dal suo ID.
             *
             * @param id l'ID del reclamo da aggiornare
             * @param stato il nuovo stato da impostare per il reclamo
             * @throws IllegalArgumentException se il reclamo con l'ID specificato non viene trovato
             */
            public void changeStato(Long id, String stato) {
                log.info("Cambio dello stato del reclamo con ID: {} a: {}", id, stato);
                Reclamo reclamo = reclamoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Reclamo non trovato"));
                reclamo.setStato(Stato.valueOf(stato));
                reclamoRepository.save(reclamo);
            }
        }

