
package it.aesse.AESSE;

import it.aesse.AESSE.converter.PrecedenteConverter;
import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.model.Precedente;
import it.aesse.AESSE.repository.PrecedenteRepository;
import it.aesse.AESSE.service.PrecedenteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class PrecedenteTest {

    @Mock
    private PrecedenteRepository precedenteRepository;

    @Mock
    private PrecedenteConverter converter;

    @InjectMocks
    private PrecedenteService precedenteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findByTipo_returnsPrecedenteList() {
        String tipo = "test tipo";
        Precedente precedente = new Precedente();
        PrecedenteDto precedenteDto = new PrecedenteDto();

        when(precedenteRepository.findByTipo(tipo)).thenReturn(Collections.singletonList(precedente));
        when(converter.toDTOList(Collections.singletonList(precedente))).thenReturn(Collections.singletonList(precedenteDto));

        List<PrecedenteDto> result = precedenteService.findByTipo(tipo);

        assertEquals(1, result.size());
        assertEquals(precedenteDto, result.get(0));
    }
    @Test
    void findByTipo_returnsEmptyListWhenNoPrecedenteFound() {
        String tipo = "test tipo";

        when(precedenteRepository.findByTipo(tipo)).thenReturn(Collections.emptyList());
        when(converter.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PrecedenteDto> result = precedenteService.findByTipo(tipo);

        assertEquals(0, result.size());
    }
    @Test
    void findByClienteId_returnsPrecedenteList() {
        Long clienteId = 1L;
        Precedente precedente = new Precedente();
        PrecedenteDto precedenteDto = new PrecedenteDto();

        when(precedenteRepository.findByClienteId(clienteId)).thenReturn(Collections.singletonList(precedente));
        when(converter.toDTOList(Collections.singletonList(precedente))).thenReturn(Collections.singletonList(precedenteDto));

        List<PrecedenteDto> result = precedenteService.findByClienteId(clienteId);

        assertEquals(1, result.size());
        assertEquals(precedenteDto, result.get(0));
    }
    @Test
    void findByClienteId_returnsEmptyListWhenNoPrecedenteFound() {
        Long clienteId = 1L;

        when(precedenteRepository.findByClienteId(clienteId)).thenReturn(Collections.emptyList());
        when(converter.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PrecedenteDto> result = precedenteService.findByClienteId(clienteId);

        assertEquals(0, result.size());
    }
}