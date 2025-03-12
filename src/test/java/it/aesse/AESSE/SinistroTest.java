// SinistroServiceTest.java
package it.aesse.AESSE;

import it.aesse.AESSE.converter.SinistroConverter;
import it.aesse.AESSE.dto.SinistroDto;
import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.model.Sinistro;
import it.aesse.AESSE.repository.SinistroRepository;
import it.aesse.AESSE.service.SinistroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SinistroTest
{
    @Mock
    private SinistroRepository sinistroRepository;

    @Mock
    private SinistroConverter sinistroConverter;

    @InjectMocks
    private SinistroService sinistroService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getSinistriByPolizza_returnsSinistri()
    {
        Long polizzaId = 1L;
        Sinistro sinistro = new Sinistro();
        SinistroDto sinistroDto = new SinistroDto();
        when(sinistroRepository.findByPolizzaId(polizzaId)).thenReturn(List.of(sinistro));
        when(sinistroConverter.toDTOList(List.of(sinistro))).thenReturn(List.of(sinistroDto));

        List<SinistroDto> result = sinistroService.getSinistriByPolizza(polizzaId);

        assertEquals(1, result.size());
    }

    @Test
    void getSinistriByPolizza_returnsEmptyListWhenNoSinistri()
    {
        Long polizzaId = 1L;
        when(sinistroRepository.findByPolizzaId(polizzaId)).thenReturn(Collections.emptyList());
        when(sinistroConverter.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<SinistroDto> result = sinistroService.getSinistriByPolizza(polizzaId);

        assertEquals(0, result.size());
    }

    @Test
    void getSommaDanniByPolizza_returnsSumOfDanni()
    {
        Long polizzaId = 1L;
        Sinistro sinistro1 = new Sinistro();
        sinistro1.setValoreDanno(BigDecimal.valueOf(100));
        Sinistro sinistro2 = new Sinistro();
        sinistro2.setValoreDanno(BigDecimal.valueOf(200));
        when(sinistroRepository.findByPolizzaId(polizzaId)).thenReturn(List.of(sinistro1, sinistro2));

        BigDecimal result = sinistroService.getSommaDanniByPolizza(polizzaId);

        assertEquals(BigDecimal.valueOf(300), result);
    }

    @Test
    void getSommaDanniByPolizza_returnsZeroWhenNoDanni()
    {
        Long polizzaId = 1L;
        when(sinistroRepository.findByPolizzaId(polizzaId)).thenReturn(Collections.emptyList());

        BigDecimal result = sinistroService.getSommaDanniByPolizza(polizzaId);

        assertEquals(BigDecimal.ZERO, result);
    }

    @Test
    void getClientFullNameForPolizza_returnsFullName()
    {
        Long polizzaId = 1L;
        Sinistro sinistro = new Sinistro();
        Polizza polizza = new Polizza();
        Cliente cliente = new Cliente();
        cliente.setNome("John");
        cliente.setCognome("Doe");
        polizza.setCliente(cliente);
        sinistro.setPolizza(polizza);
        when(sinistroRepository.findByPolizzaId(polizzaId)).thenReturn(List.of(sinistro));

        String result = sinistroService.getClientFullNameForPolizza(polizzaId);

        assertEquals("John Doe", result);
    }

    @Test
    void getClientFullNameForPolizza_returnsUnknownWhenNoClient()
    {
        Long polizzaId = 1L;
        when(sinistroRepository.findByPolizzaId(polizzaId)).thenReturn(Collections.emptyList());

        String result = sinistroService.getClientFullNameForPolizza(polizzaId);

        assertEquals("Unknown", result);
    }
}