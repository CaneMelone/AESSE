package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.service.PagamentoService;
import it.aesse.AESSE.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Reclamo")
@CrossOrigin(origins = "http://localhost:8080")
public class ReclamoController extends AbstractController<ReclamoDto> {
    @Autowired
    private ReclamoService reclamoService;


    @GetMapping("/findByDate")
    public List<ReclamoDto> findByDate(@RequestParam LocalDate date) {
        return reclamoService.findByDate(date);
    }

    @GetMapping("/findByMotivo")
    public List<ReclamoDto> findByMotivo(@RequestParam String motivo) {
        return reclamoService.findByMotivo(motivo);
    }

    @GetMapping("/findByNome")
    public List<ReclamoDto> findByNome(@RequestParam String nome) {
        return reclamoService.findByNome(nome);
    }

    @GetMapping("/findByStato")
    public List<ReclamoDto> findByStato(@RequestParam String stato) {
        return reclamoService.findByStato(stato);
    }
    @GetMapping("/Getreport")
    public List<ReclamoDto> findByCriteria(@RequestParam("valore") String valore,
                                           @RequestParam("controllo") String controllo) {
        return reclamoService.findByCriteria(valore, controllo);
    }


}
