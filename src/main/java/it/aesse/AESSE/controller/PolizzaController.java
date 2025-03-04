package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.dto.PolizzaDto;
import it.aesse.AESSE.service.PolizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Polizza")
@CrossOrigin(origins = "http://localhost:4200")
public class PolizzaController extends AbstractController<PolizzaDto> {
    @Autowired
    private PolizzaService polizzaService;
}