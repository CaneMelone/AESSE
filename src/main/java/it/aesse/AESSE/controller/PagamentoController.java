package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Pagamento")
@CrossOrigin(origins = "http//localhost:8080")
public class PagamentoController extends AbstractController<BeneDto> {
    @Autowired
    private PagamentoService pagamentoService;
}