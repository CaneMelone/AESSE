package it.aesse.AESSE;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.repository.ClienteRepository;
import it.aesse.AESSE.repository.PolizzaRepository;
import it.aesse.AESSE.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ClienteTest {

    @Mock
    private PolizzaRepository polizzaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente1;
    private Cliente cliente2;
    private Polizza polizzaAttiva;
    private Polizza polizzaScaduta;

    @BeforeEach
    void setUp() {
        cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Mario");
        cliente1.setCognome("Rossi");
        cliente1.setTelefono("123456789");
        cliente1.setEmail("mario.rossi@email.com");

        cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Luca");
        cliente2.setCognome("Bianchi");
        cliente2.setTelefono("987654321");
        cliente2.setEmail("luca.bianchi@email.com");

        polizzaAttiva = new Polizza();
        polizzaAttiva.setStato("Attiva");

        polizzaScaduta = new Polizza();
        polizzaScaduta.setStato("Scaduta");
    }

    @Test
    void testHasPolizzeAttive_True() {
        when(polizzaRepository.findByClienteId(1L)).thenReturn(List.of(polizzaAttiva));

        boolean result = clienteService.hasPolizzeAttive(1L);

        assertTrue(result);
        verify(polizzaRepository, times(1)).findByClienteId(1L);
    }

    @Test
    void testHasPolizzeAttive_False() {
        when(polizzaRepository.findByClienteId(2L)).thenReturn(List.of(polizzaScaduta));

        boolean result = clienteService.hasPolizzeAttive(2L);

        assertFalse(result);
        verify(polizzaRepository, times(1)).findByClienteId(2L);
    }

    @Test
    void testGetInformazioniDiContatto() {
        when(clienteRepository.findAll()).thenReturn(List.of(cliente1, cliente2));
        when(polizzaRepository.findByClienteId(1L)).thenReturn(List.of(polizzaAttiva));
        when(polizzaRepository.findByClienteId(2L)).thenReturn(List.of(polizzaScaduta));

        List<Map<String, String>> result = clienteService.getInformazioniDiContatto();

        assertEquals(1, result.size());
        assertEquals("Mario Rossi", result.get(0).get("nome"));
        assertEquals("123456789", result.get(0).get("telefono"));
        assertEquals("mario.rossi@email.com", result.get(0).get("email"));

        verify(clienteRepository, times(1)).findAll();
        verify(polizzaRepository, times(1)).findByClienteId(1L);
        verify(polizzaRepository, times(1)).findByClienteId(2L);
    }
}
