package it.aesse.AESSE;

import it.aesse.AESSE.repository.BeneRepository;
import it.aesse.AESSE.repository.PolizzaRepository;
import it.aesse.AESSE.service.BeneService;
import it.aesse.AESSE.sub.PolizzaBene;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BeneTest {

    @Mock
    private BeneRepository beneRepository;

    @Mock
    private PolizzaRepository polizzaRepository;

    @InjectMocks
    private BeneService beneService;

    @BeforeEach
    void setUp() {
        // Reset del comportamento dei mock prima di ogni test
        Mockito.reset(beneRepository, polizzaRepository);
    }

    @Test
    void testFindBeniByClienteId() {
        Long clienteId = 1L;
        List<PolizzaBene> expectedBeni = Collections.emptyList();

        when(polizzaRepository.findByCliente_Id(clienteId)).thenReturn(expectedBeni);

        List<PolizzaBene> result = beneService.findBeniByClienteId(clienteId);

        assertNotNull(result);
        assertEquals(expectedBeni, result);
        verify(polizzaRepository, times(1)).findByCliente_Id(clienteId);
    }

    @Test
    void testCountPoliciesForBene() {
        Long beneId = 2L;
        int expectedCount = 5;

        when(polizzaRepository.countByBeneId(beneId)).thenReturn(expectedCount);

        int result = beneService.countPoliciesForBene(beneId);

        assertEquals(expectedCount, result);
        verify(polizzaRepository, times(1)).countByBeneId(beneId);
    }
}
