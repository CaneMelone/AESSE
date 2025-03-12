package it.aesse.AESSE;

import it.aesse.AESSE.dto.PolizzaDto;
import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.repository.PolizzaRepository;
import it.aesse.AESSE.service.PolizzaService;
import it.aesse.AESSE.service.EmailService;
import it.aesse.AESSE.converter.PolizzaConverter;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PolizzaTest
{
    @Mock
    private PolizzaRepository polizzaRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private PolizzaConverter converter;

    @InjectMocks
    private PolizzaService polizzaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void aggiornaStatoPolizza_updatesStatusAndStartDate()
    {
        Long idPolizza = 1L;
        String newState = "ACTIVE";
        Polizza polizza = new Polizza();
        polizza.setId_polizza(idPolizza);

        when(polizzaRepository.findById(idPolizza)).thenReturn(Optional.of(polizza));

        polizzaService.aggiornaStatoPolizza(idPolizza, newState);

        assertEquals(newState, polizza.getStato());
        assertEquals(LocalDate.now(), polizza.getData_inizio());
        verify(polizzaRepository, times(1)).save(polizza);
    }

    @Test
    void aggiornaStatoPolizza_throwsExceptionWhenPolizzaNotFound()
    {
        Long idPolizza = 1L;
        String newState = "ACTIVE";

        when(polizzaRepository.findById(idPolizza)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> polizzaService.aggiornaStatoPolizza(idPolizza, newState));
    }

    @Test
    void insert_savesPolizzaAndSendsEmail()
    {
        PolizzaDto dto = new PolizzaDto();
        Polizza polizza = new Polizza();
        Polizza savedPolizza = new Polizza();
        Cliente cliente = new Cliente();
        cliente.setEmail("test@example.com");
        savedPolizza.setCliente(cliente);

        when(converter.toEntity(dto)).thenReturn(polizza);
        when(polizzaRepository.save(polizza)).thenReturn(savedPolizza);
        when(converter.toDTO(savedPolizza)).thenReturn(dto);

        PolizzaDto result = polizzaService.insert(dto);

        assertEquals(dto, result);
        verify(emailService, times(1)).sendEmailCreazionePolizza("test@example.com");
    }

    @Test
    void insert_throwsExceptionWhenEmailServiceFails()
    {
        PolizzaDto dto = new PolizzaDto();
        Polizza polizza = new Polizza();
        Polizza savedPolizza = new Polizza();
        Cliente cliente = new Cliente();
        cliente.setEmail("test@example.com");
        savedPolizza.setCliente(cliente);

        when(converter.toEntity(dto)).thenReturn(polizza);
        when(polizzaRepository.save(polizza)).thenReturn(savedPolizza);
        when(converter.toDTO(savedPolizza)).thenReturn(dto);
        doThrow(new RuntimeException("Email service failed")).when(emailService).sendEmailCreazionePolizza("test@example.com");

        assertThrows(RuntimeException.class, () -> polizzaService.insert(dto));
    }
}