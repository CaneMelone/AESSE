package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.SinistroDto;
import it.aesse.AESSE.service.SinistroService;
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
@RequestMapping("/Sinistro")
@CrossOrigin(origins = "http://localhost:8080")
public class SinistroController extends AbstractController<SinistroDto>
{
    @Autowired
    private SinistroService sinistroService;

    //metodo per recuperare i sinistri in base all'id della polizza
    @GetMapping("/byPolizza")
    public List<SinistroDto> getSinistriByPolizza(@RequestParam("id") Long polizzaId)
    {
        return sinistroService.getSinistriByPolizza(polizzaId);
    }

    //metodo per recuperare i sinistri in base allo stato
    @GetMapping("/byStato")
    public List<SinistroDto> getSinistriByStato(@RequestParam("stato") String stato)
    {
        return sinistroService.getSinistriByStato(stato);
    }

    //metodo per recuperare i sinistri in base al cliente
    @GetMapping("/byCliente")
    public List<SinistroDto> getSinistriByCliente(@RequestParam("id") Long clienteId)
    {
        return sinistroService.getSinistriByCliente(clienteId);
    }

    //metodo per calcolare il totale dei danni di una polizza
    @GetMapping("/sommaDanni")
    public BigDecimal getSommaDanniByPolizza(@RequestParam("id") Long clienteId)
    {
        return sinistroService.getSommaDanniByPolizza(clienteId);
    }

    //metodo per calcolare il totale dei danni concessi di una polizza
    @GetMapping("/sommaConcessi")
    public BigDecimal getSommaConcessiByPolizza(@RequestParam("id") Long clienteId)
    {
        return sinistroService.getSommaConcessiByPolizza(clienteId);
    }

    //metodo che restituisce il file excel con i sinistri di una polizza
    @GetMapping("/ExcelByPolizza")
    public ResponseEntity<byte[]> downloadExcelByPolizza(@RequestParam("id") Long polizzaId)
    {
        try
        {
            //genera il file excel
            byte[] excelBytes = sinistroService.generateExcelByPolizza(polizzaId);
            //prendi i dati
            String clientFullName = sinistroService.getClientFullNameForPolizza(polizzaId);
            String currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //definisci il nome del file
            String filename = "SinistriPolizza" + polizzaId + "_" + clientFullName + "_" + currentDate + ".xlsx";
            //definisci i parametri del file
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", filename);
            headers.setContentLength(excelBytes.length);
            //ritorna il file
            return ResponseEntity.ok().headers(headers).body(excelBytes);
        } catch (IOException e)
        {
            return ResponseEntity.status(500).body(null);
        }
    }
}