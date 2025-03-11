package it.aesse.AESSE.controller;

    import it.aesse.AESSE.dto.BeneDto;
    import it.aesse.AESSE.sub.PolizzaBene;
    import it.aesse.AESSE.service.BeneService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

@RestController
    @RequestMapping("/Bene")
    @CrossOrigin(origins = "http://localhost:8080")
    public class BeneController extends AbstractController<BeneDto>
{
        @Autowired
        private BeneService beneService;

        //Metodo per trovare i beni di un cliente
        @GetMapping("/benidacliente")
        public List<BeneDto> findBeniByClienteId(@RequestParam("clienteId") Long clienteId)
        {
            return beneService.findBeniByClienteId(clienteId);
        }

        //Metodo per contare le polizze di un bene
        @GetMapping("/countpolizze")
        public int countPoliciesForBene(@RequestParam("beneId") Long beneId)
        {
            return beneService.countPoliciesForBene(beneId);
        }
}