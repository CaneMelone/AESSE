package it.aesse.AESSE;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import it.aesse.AESSE.converter.Converter;
import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.model.Reclamo;
import it.aesse.AESSE.repository.ReclamoRepository;
import it.aesse.AESSE.service.ReclamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ReclamoServiceTest {
    @Mock
    private ReclamoRepository reclamoRepository;

    @Mock
    private Converter<Reclamo, ReclamoDto> converter;

    @InjectMocks
    private ReclamoService reclamoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByCriteria_withNome_returnsReclamoDtoList() {
        Reclamo reclamo = new Reclamo();
        ReclamoDto reclamoDto = new ReclamoDto();
        when(reclamoRepository.findByNome("Mario")).thenReturn(Collections.singletonList(reclamo));
        when(converter.toDTOList(Collections.singletonList(reclamo))).thenReturn(Collections.singletonList(reclamoDto));

        List<ReclamoDto> result = reclamoService.findByCriteria("Mario", "nome");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reclamoDto, result.get(0));
    }

    @Test
    void findByCriteria_withData_returnsReclamoDtoList() {
        LocalDate date = LocalDate.of(2023, 10, 1);
        Reclamo reclamo = new Reclamo();
        ReclamoDto reclamoDto = new ReclamoDto();
        when(reclamoRepository.findByData(date)).thenReturn(Collections.singletonList(reclamo));
        when(converter.toDTOList(Collections.singletonList(reclamo))).thenReturn(Collections.singletonList(reclamoDto));

        List<ReclamoDto> result = reclamoService.findByCriteria("2023-10-01", "data");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reclamoDto, result.get(0));
    }

    @Test
    void findByCriteria_withMotivo_returnsReclamoDtoList() {
        Reclamo reclamo = new Reclamo();
        ReclamoDto reclamoDto = new ReclamoDto();
        when(reclamoRepository.findByMotivo("Complaint")).thenReturn(Collections.singletonList(reclamo));
        when(converter.toDTOList(Collections.singletonList(reclamo))).thenReturn(Collections.singletonList(reclamoDto));

        List<ReclamoDto> result = reclamoService.findByCriteria("Complaint", "motivo");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reclamoDto, result.get(0));
    }

    @Test
    void findByCriteria_withStato_returnsReclamoDtoList() {
        Reclamo reclamo = new Reclamo();
        ReclamoDto reclamoDto = new ReclamoDto();
        when(reclamoRepository.findByStato("Open")).thenReturn(Collections.singletonList(reclamo));
        when(converter.toDTOList(Collections.singletonList(reclamo))).thenReturn(Collections.singletonList(reclamoDto));

        List<ReclamoDto> result = reclamoService.findByCriteria("Open", "stato");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reclamoDto, result.get(0));
    }

    @Test
    void findByCriteria_withInvalidControllo_returnsAllReclamoDtoList() {
        Reclamo reclamo = new Reclamo();
        ReclamoDto reclamoDto = new ReclamoDto();
        when(reclamoRepository.findAll()).thenReturn(Collections.singletonList(reclamo));
        when(converter.toDTOList(Collections.singletonList(reclamo))).thenReturn(Collections.singletonList(reclamoDto));

        List<ReclamoDto> result = reclamoService.findByCriteria("AnyValue", "invalid");

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(reclamoDto, result.get(0));
    }
}