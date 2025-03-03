package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.dto.SinistroDto;
import it.aesse.AESSE.service.PagamentoService;
import it.aesse.AESSE.service.SinistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Sinistro")
@CrossOrigin(origins = "http//localhost:8080")
public class SinistroController extends AbstractController<SinistroDto> {
    @Autowired
    private SinistroService sinistroService;
}
