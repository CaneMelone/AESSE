package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.service.PagamentoService;
import it.aesse.AESSE.service.ReclamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Reclamo")
@CrossOrigin(origins = "http://localhost:8080")
public class ReclamoController extends AbstractController<ReclamoDto> {
    @Autowired
    private ReclamoService reclamoService;
}
