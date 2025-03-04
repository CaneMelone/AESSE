package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.dto.PagamentoDto;
import it.aesse.AESSE.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/Pagamento")
@CrossOrigin(origins = "http//localhost:8080")
public class PagamentoController extends AbstractController<PagamentoDto> {
    @Autowired
    private PagamentoService pagamentoService;

    @GetMapping("/byPolizza")
    public List<PagamentoDto> getPagamentiByPolizza(@RequestParam("id") Long polizzaId) {
        return pagamentoService.getPagamentiByPolizza(polizzaId);
    }

    @GetMapping("/byMetodo")
    public List<PagamentoDto> getPagamentiByMetodo(@RequestParam("metodo") String metodo) {
        return pagamentoService.getPagamentiByMetodo(metodo);
    }

    @GetMapping("/sommaPolizza")
    public BigDecimal getSommaPagamentiPolizza(@RequestParam("id") Long polizzaId) {
        return pagamentoService.getSommaPagamentiPolizza(polizzaId);
    }
}