package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.PagamentoDto;
import it.aesse.AESSE.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/Pagamento")
@CrossOrigin(origins = "http://localhost:8080")
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

    @GetMapping("/sommaPerAnno")
    public BigDecimal getSommaPagamentiByPolizzaAndYear(@RequestParam("id") Long polizzaId,
                                                        @RequestParam("anno") int anno) {
        return pagamentoService.getSommaPagamentiByPolizzaAndYear(polizzaId, anno);
    }
    @GetMapping("/ExcelByPolizza")
    public ResponseEntity<byte[]> downloadExcelByPolizza(@RequestParam("id") Long polizzaId) {
        try {
            byte[] excelBytes = pagamentoService.generateExcelByPolizza(polizzaId);
            String clientFullName = pagamentoService.getClientFullNameForPolizza(polizzaId);
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String filename = "PagamentiPolizza" + polizzaId + "_" + clientFullName + "_" + currentDate + ".xlsx";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setContentLength(excelBytes.length);
            return ResponseEntity.ok().headers(headers).body(excelBytes);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}
