// PrecedenteService.java
package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.Converter;
import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.model.Precedente;
import it.aesse.AESSE.repository.PrecedenteRepository;
import it.aesse.AESSE.sub.gravita_crimine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PrecedenteService extends AbstractService<Precedente, PrecedenteDto> {

    @Autowired
    private PrecedenteRepository precedenteRepository;

    @Autowired
    private Converter<Precedente, PrecedenteDto> converter;

    /**
     * Recupera una lista di oggetti PrecedenteDto per il cliente specificato e verifica se il cliente
     * ha una pena e in che stato si trova.
     *
     * @param clienteId l'ID del cliente per filtrare gli oggetti PrecedenteDto
     * @return un oggetto PrecedenteDto con le informazioni sui precedenti e sulla pena e il suo stato
     */
    public Optional<PrecedenteDto> getPrecedentiEVerificaPena(Long clienteId) {
        log.info("Recupero dei precedenti e verifica della pena per il cliente con ID: {}", clienteId);
        List<Precedente> precedenti = precedenteRepository.findByClienteId(clienteId);
        return precedenti.stream().findFirst().map(converter::toDTO);
    }

   /**
    * Calcola il premio finale in base ai precedenti del cliente.
    *
    * Questo metodo recupera la lista dei precedenti per l'ID cliente specificato.
    * Successivamente, itera attraverso ogni precedente per regolare il premio iniziale.
    * Se il precedente è contrassegnato come scontato, il premio viene ridotto
    * della percentuale specificata nel precedente. Altrimenti, il premio viene aumentato
    * della stessa percentuale. Inoltre, se il precedente non è scontato, il premio viene
    * ulteriormente ridotto in base alla gravità del crimine. Se la gravità è "Inaccettabile",
    * il premio finale sarà 0.
    *
    * @param clienteId l'ID del cliente
    * @param premioIniziale il premio iniziale
    * @return il premio finale calcolato
    */
   public double calcolaPremioFinale(Long clienteId, double premioIniziale) {
       log.info("Calcolo del premio finale per il cliente con ID: {}", clienteId);
       List<Precedente> precedenti = precedenteRepository.findByClienteId(clienteId);
       double premioFinale = premioIniziale;

       for (Precedente precedente : precedenti) {
           if (precedente.getGravita() == gravita_crimine.Inaccettabile) {
               return 0; // Nessun premio per gravità inaccettabile
           }
           if (precedente.getScontata()) {
               premioFinale -= premioFinale * (precedente.getPercentuale() / 100);
           } else {
               premioFinale -= premioFinale * (precedente.getPercentuale() / 100);
               switch (precedente.getGravita()) {
                   case Alta:
                       premioFinale -= premioFinale * 0.20; // Riduzione del 20% per gravità alta
                       break;
                   case Media:
                       premioFinale -= premioFinale * 0.10; // Riduzione del 10% per gravità media
                       break;
                   case Bassa:
                       premioFinale -= premioFinale * 0.05; // Riduzione del 5% per gravità bassa
                       break;
               }
           }
       }

       return premioFinale;
   }

   }

             //TODO implementa un metodo che diminuisce
             // il premio in base a quali e quanti precedenti
             // ha il cliente e se li ha scontati
             // e aumentare il prezzo per ogni precedente esempio se ha 2 precedenti
             // dovra fare totale + percentuale 1 precedente poi rifara con Nuovo totale e 2 percentuale
             // e cosi via




             //TODO fai in modo tale che alla creazione
             // della polizza mi dia il valore finale sulla base
             // di una tabella della maggiorazione dei sinistri

