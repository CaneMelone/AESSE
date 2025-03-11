package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.service.PrecedenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Precedente")
@CrossOrigin(origins = "http//localhost:8080")
public class PrecedenteController extends AbstractController<PrecedenteDto>{
    @Autowired
    private PrecedenteService precedenteService;

    //metodo per recuperare i precedenti in base al tipo
    @GetMapping("/byTipo")
    public List<PrecedenteDto> getPrecedentiByTipo(@RequestParam("tipo") String tipo)
    {
        //richiamo il metodo del service
        return precedenteService.findByTipo(tipo);
    }

    //metodo per recuperare i precedenti in base all'ID del cliente
    @GetMapping("/byClienteId")
    public List<PrecedenteDto> getPrecedentiByClienteId(@RequestParam("clienteId") Long clienteId)
    {
        //richiamo il metodo del service
        return precedenteService.findByClienteId(clienteId);
    }
}