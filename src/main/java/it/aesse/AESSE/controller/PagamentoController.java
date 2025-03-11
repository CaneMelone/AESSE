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
public class PagamentoController extends AbstractController<PagamentoDto>
{
    @Autowired
    private PagamentoService pagamentoService;

    //Metodo per avere i pagamenti di una polizza
    @GetMapping("/byPolizza")
    public List<PagamentoDto> getPagamentiByPolizza(@RequestParam("id") Long polizzaId)
    {
        return pagamentoService.getPagamentiByPolizza(polizzaId);
    }

    //Metodo per avere i pagamenti secondo il metodo
    @GetMapping("/byMetodo")
    public List<PagamentoDto> getPagamentiByMetodo(@RequestParam("metodo") String metodo)
    {
        return pagamentoService.getPagamentiByMetodo(metodo);
    }

    //Metodo per avere il totale dei pagamenti di una polizza
    @GetMapping("/sommaPolizza")
    public BigDecimal getSommaPagamentiPolizza(@RequestParam("id") Long polizzaId)
    {
        return pagamentoService.getSommaPagamentiPolizza(polizzaId);
    }

    //Metodo per avere il totale dei pagamenti di una polizza in un anno
    @GetMapping("/sommaPerAnno")
    public BigDecimal getSommaPagamentiByPolizzaAndYear(@RequestParam("id") Long polizzaId,
                                                        @RequestParam("anno") int anno)
    {
        return pagamentoService.getSommaPagamentiByPolizzaAndYear(polizzaId, anno);
    }

    //Metodo per generare un file excel con i pagamenti di una polizza
    @GetMapping("/ExcelByPolizza")
    public ResponseEntity<byte[]> downloadExcelByPolizza(@RequestParam("id") Long polizzaId)
    {
        try
        {
            //genera il file excel
            byte[] excelBytes = pagamentoService.generateExcelByPolizza(polizzaId);
            //prendo i dati
            String clientFullName = pagamentoService.getClientFullNameForPolizza(polizzaId);
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //definisco il nome del file
            String filename = "PagamentiPolizza" + polizzaId + "_" + clientFullName + "_" + currentDate + ".xlsx";
            //definisco i parametri del file
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setContentLength(excelBytes.length);
            //ritorno il file
            return ResponseEntity.ok().headers(headers).body(excelBytes);
        } catch (IOException e)
        {
            return ResponseEntity.status(500).body(null);
        }
    }
}