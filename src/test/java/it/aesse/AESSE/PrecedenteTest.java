
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
    void findByDescription_returnsPrecedenteList() {
        String description = "test description";
        Precedente precedente = new Precedente();
        PrecedenteDto precedenteDto = new PrecedenteDto();

        when(precedenteRepository.findByDescription(description)).thenReturn(Collections.singletonList(precedente));
        when(converter.toDTOList(Collections.singletonList(precedente))).thenReturn(Collections.singletonList(precedenteDto));

        List<PrecedenteDto> result = precedenteService.findByDescription(description);

        assertEquals(1, result.size());
        assertEquals(precedenteDto, result.get(0));
    }
    @Test
    void findByDescription_returnsEmptyListWhenNoPrecedenteFound() {
        String description = "test description";

        when(precedenteRepository.findByDescription(description)).thenReturn(Collections.emptyList());
        when(converter.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PrecedenteDto> result = precedenteService.findByDescription(description);

        assertEquals(0, result.size());
    }
    @Test
    void findByCaseId_returnsPrecedenteList() {
        Long caseId = 1L;
        Precedente precedente = new Precedente();
        PrecedenteDto precedenteDto = new PrecedenteDto();

        when(precedenteRepository.findByCaseId(caseId)).thenReturn(Collections.singletonList(precedente));
        when(converter.toDTOList(Collections.singletonList(precedente))).thenReturn(Collections.singletonList(precedenteDto));

        List<PrecedenteDto> result = precedenteService.findByCaseId(caseId);

        assertEquals(1, result.size());
        assertEquals(precedenteDto, result.get(0));
    }
    @Test
    void findByCaseId_returnsEmptyListWhenNoPrecedenteFound() {
        Long caseId = 1L;

        when(precedenteRepository.findByCaseId(caseId)).thenReturn(Collections.emptyList());
        when(converter.toDTOList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<PrecedenteDto> result = precedenteService.findByCaseId(caseId);

        assertEquals(0, result.size());
    }
}