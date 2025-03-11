package it.aesse.AESSE.controller;

import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.repository.ClienteRepository;
import it.aesse.AESSE.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Cliente")
@CrossOrigin(origins = "http://localhost:8080")
public class ClienteController extends AbstractController<ClienteDto>
{
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    //Metodo per avere le informazioni di contatto di un cliente con polizza
    @GetMapping("/contattoclienteconpolizza")
    public List<Map<String, String>> getInformazioniDiContatto() {
        return clienteService.getInformazioniDiContatto();
    }

    //Metodo per vedere se il cliente ha una polizza attiva
    @GetMapping("/isclienteconpolizza")
    public boolean hasPolizzeAttive(@RequestParam("clienteId") Long clienteId)
    {
        return clienteService.hasPolizzeAttive(clienteId);
    }

    //Metodo che restituisce un file excel con le informazioni del cliente
    @GetMapping("/export-cliente")
    public ResponseEntity<byte[]> exportCliente(@RequestParam("clienteId") Long idCliente)
    {
        try
        {
            //prendi il cliente
            Cliente cliente = clienteRepository.getClienteById(idCliente);
            if (cliente == null)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            //genera il file excel
            byte[] file = clienteService.generaExcelCliente(cliente);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cliente_" + idCliente + ".xlsx")
                    .body(file);

        } catch (IOException e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}