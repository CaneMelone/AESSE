package it.aesse.AESSE.controller;

    import it.aesse.AESSE.dto.BeneDto;
    import it.aesse.AESSE.service.BeneService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/Bene")
    @CrossOrigin(origins = "http//localhost:8080")
    public class BeneController extends AbstractController<BeneDto> {
        @Autowired
        private BeneService beneService;
    }