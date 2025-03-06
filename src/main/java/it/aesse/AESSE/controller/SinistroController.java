package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.SinistroDto;
import it.aesse.AESSE.service.SinistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/Sinistro")
@CrossOrigin(origins = "http://localhost:8080")
public class SinistroController extends AbstractController<SinistroDto> {

    @Autowired
    private SinistroService sinistroService;

    @GetMapping("/byPolizza")
    public List<SinistroDto> getSinistriByPolizza(@RequestParam("id") Long polizzaId) {
        return sinistroService.getSinistriByPolizza(polizzaId);
    }

    @GetMapping("/byStato")
    public List<SinistroDto> getSinistriByStato(@RequestParam("stato") String stato) {
        return sinistroService.getSinistriByStato(stato);
    }

    @GetMapping("/byCliente")
    public List<SinistroDto> getSinistriByCliente(@RequestParam("id") Long clienteId) {
        return sinistroService.getSinistriByCliente(clienteId);
    }

    @GetMapping("/sommaDanni")
    public BigDecimal getSommaDanniByPolizza(@RequestParam("id") Long clienteId) {
        return sinistroService.getSommaDanniByPolizza(clienteId);
    }

    @GetMapping("/sommaConcessi")
    public BigDecimal getSommaConcessiByPolizza(@RequestParam("id") Long clienteId) {
        return sinistroService.getSommaConcessiByPolizza(clienteId);
    }
}
