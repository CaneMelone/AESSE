package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.service.PrecedenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/Precedente")
@CrossOrigin(origins = "http://localhost:8080")
public class PrecedenteController extends AbstractController<PrecedenteDto> {
    @Autowired
    private PrecedenteService precedenteService;

    @GetMapping("/getVerificaPena")
    public Optional<PrecedenteDto> getPrecedentiEVerificaPena(@RequestParam Long clienteId) {
        return precedenteService.getPrecedentiEVerificaPena(clienteId);
    }
    @GetMapping("/calcolaPremio")
    public double calcolaPremioFinale(@RequestParam Long clienteId,
                                      @RequestParam double premioIniziale) {
        return precedenteService.calcolaPremioFinale(clienteId, premioIniziale);
    }
}