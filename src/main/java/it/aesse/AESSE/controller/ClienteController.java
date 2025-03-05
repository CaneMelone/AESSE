package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Cliente")
@CrossOrigin(origins = "http://localhost:8080")
public class ClienteController extends AbstractController<ClienteDto>{
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/contattoclienteconpolizza")
    public List<Map<String, String>> getInformazioniDiContatto() {
        return clienteService.getInformazioniDiContatto();
    }

    @GetMapping("/isclienteconpolizza")
    public boolean hasPolizzeAttive(@RequestParam("clienteId") Long clienteId) {
        return clienteService.hasPolizzeAttive(clienteId);
    }
}
