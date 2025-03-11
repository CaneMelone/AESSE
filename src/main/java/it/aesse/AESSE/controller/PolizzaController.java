// PolizzaController.java
package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.StatoRequest;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.service.PolizzaService;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Polizza")
@CrossOrigin(origins = "http://localhost:4200")
public class PolizzaController
{
    private static final Logger logger = LoggerFactory.getLogger(PolizzaController.class);

    @Autowired
    private PolizzaService polizzaService;

    //Metodo per aggiornare lo stato di una polizza
    @PutMapping("/aggiornaStato/{id}")
    public ResponseEntity<String> aggiornaStatoPolizza(@PathVariable("id") Long id_polizza, @RequestBody StatoRequest request)
    {
        logger.info("Richiesta di aggiornamento stato polizza ricevuta. ID: {}, Nuovo Stato: {}", id_polizza, request.getStato());

        try
        {
            //richiama la funzione del service
            polizzaService.aggiornaStatoPolizza(id_polizza, request.getStato());
            logger.info("Stato della polizza con ID {} aggiornato con successo a '{}'", id_polizza, request.getStato());
            return ResponseEntity.ok("Stato della polizza aggiornato con successo.");
        } catch (EntityNotFoundException e)
        {
                logger.error("Errore: Polizza con ID {} non trovata.", id_polizza, e);
                return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    //Metodo per avere la polizza dall'id
    @GetMapping("/{id}")
    public ResponseEntity<Polizza> getPolizzaById(@PathVariable("id") Long id)
    {
        logger.info("Richiesta di ricerca polizza ricevuta. ID: {}", id);
        try
        {
            //richiama la funzione del service
            Polizza polizza = polizzaService.findById(id);
            logger.info("Polizza trovata: ID: {}, Stato: {}", polizza.getId_polizza(), polizza.getStato());
            return ResponseEntity.ok(polizza);
        } catch (EntityNotFoundException e)
        {
            logger.error("Errore: Polizza con ID {} non trovata.", id, e);
            return ResponseEntity.status(404).body(null);
        }
    }
}