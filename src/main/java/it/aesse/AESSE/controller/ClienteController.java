package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class ClienteController extends AbstractController<ClienteDto>{
    @Autowired
    ClienteService clienteService;

    @GetMapping("/contattocliente")
    public List<Map<String, String>> getInformazioniDiContatto() {
        return clienteService.getInformazioniDiContatto();
    }

    @GetMapping("/isclienteconpolizza")
    public boolean hasPolizzeAttve(Long idCliente) {
        return clienteService.hasPolizzeAttve(idCliente);
    }
}
