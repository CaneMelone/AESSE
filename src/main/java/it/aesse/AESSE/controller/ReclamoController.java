package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/Reclamo")
@CrossOrigin(origins = "http://localhost:8080")
public class ReclamoController extends AbstractController<ReclamoDto>
{
    @Autowired
    private ReclamoService reclamoService;

    //metodo per recuperare i reclami in base alla data
    @GetMapping("/findByDate")
    public List<ReclamoDto> findByDate(@RequestParam LocalDate date) {
        return reclamoService.findByDate(date);
    }

    //metodo per recuperare i reclami in base al motivo
    @GetMapping("/findByMotivo")
    public List<ReclamoDto> findByMotivo(@RequestParam String motivo) {
        return reclamoService.findByMotivo(motivo);
    }

    //metodo per recuperare i reclami in base al nome
    @GetMapping("/findByNome")
    public List<ReclamoDto> findByNome(@RequestParam String nome) {
        return reclamoService.findByNome(nome);
    }

    //metodo per recuperare i reclami in base allo stato
    @GetMapping("/findByStato")
    public List<ReclamoDto> findByStato(@RequestParam String stato) {
        return reclamoService.findByStato(stato);
    }

    //metodo per recuperare i reclami in base a qualunque criterio
    @GetMapping("/Getreport")
    public List<ReclamoDto> findByCriteria(@RequestParam("valore") String valore,
                                           @RequestParam("controllo") String controllo)
    {
        return reclamoService.findByCriteria(valore, controllo);
    }

    //metodo per cambiare lo stato di un reclamo
    @GetMapping("/changeStato")
    public void changeStato(@RequestParam Long id,
                            @RequestParam String stato)
    {
        reclamoService.changeStato(id, stato);
    }
}