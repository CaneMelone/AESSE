package it.aesse.AESSE;

import it.aesse.AESSE.converter.PagamentoConverter;
import it.aesse.AESSE.dto.PagamentoDto;
import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.model.Pagamento;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.repository.PagamentoRepository;
import it.aesse.AESSE.service.PagamentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagamentoTest {

    @Mock
    private PagamentoRepository pagamentoRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoConverter converter;


    private Pagamento pagamento;
    private Polizza polizza;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Mario");
        cliente.setCognome("Rossi");
        cliente.setEmail("mario.rossi@example.com");

        polizza = new Polizza();
        polizza.setId_polizza(1L);
        polizza.setCliente(cliente);
        polizza.setTipo("Auto");
        polizza.setStato("Attiva");
        polizza.setPremio(new BigDecimal("500.00"));

        pagamento = new Pagamento();
        pagamento.setId_pagamento(1L);
        pagamento.setPolizza(polizza);
        pagamento.setData_pagamento(LocalDate.of(2024, 3, 7));
        pagamento.setImporto(new BigDecimal("100.00"));
        pagamento.setMetodo("Bonifico");
        pagamento.setCausale("Pagamento rata marzo");
    }

    @Test
    void getPagamentiByPolizza_ShouldReturnPagamenti() {
        Long polizzaId = 1L;
        List<Pagamento> mockPagamenti = List.of(new Pagamento());
        List<PagamentoDto> mockDtoPagamenti = List.of(new PagamentoDto());

        when(pagamentoRepository.findByPolizzaId(polizzaId)).thenReturn(mockPagamenti);
        when(converter.toDTOList(mockPagamenti)).thenReturn(mockDtoPagamenti); // <--- MOCKARE IL CONVERTER

        List<PagamentoDto> result = pagamentoService.getPagamentiByPolizza(polizzaId);

        assertEquals(mockDtoPagamenti, result);
        verify(pagamentoRepository).findByPolizzaId(polizzaId);
        verify(converter).toDTOList(mockPagamenti);  // <--- VERIFICHIAMO CHE VIENE USATO
    }

    @Test
    void getPagamentiByMetodo_ShouldReturnPagamenti() {
        when(pagamentoRepository.findByMetodo("Bonifico")).thenReturn(List.of(pagamento));
        when(converter.toDTOList(List.of(pagamento))).thenReturn(List.of(new PagamentoDto())); // <--- MOCKA IL CONVERTER

        List<PagamentoDto> result = pagamentoService.getPagamentiByMetodo("Bonifico");

        assertNotNull(result);
        assertEquals(1, result.size());  // ✅ Ora dovrebbe passare
    }

    @Test
    void getSommaPagamentiPolizza_ShouldReturnCorrectSum() {
        when(pagamentoRepository.findByPolizzaId(1L)).thenReturn(List.of(pagamento));
        BigDecimal sum = pagamentoService.getSommaPagamentiPolizza(1L);
        assertEquals(new BigDecimal("100.00"), sum);
    }

    @Test
    void getSommaPagamentiByPolizzaAndYear_ShouldReturnCorrectSum() {
        when(pagamentoRepository.findByPolizzaId(1L)).thenReturn(List.of(pagamento));
        BigDecimal sum = pagamentoService.getSommaPagamentiByPolizzaAndYear(1L, 2024);
        assertEquals(new BigDecimal("100.00"), sum);
    }

    @Test
    void getClientFullNameForPolizza_ShouldReturnFullName() {
        when(pagamentoRepository.findByPolizzaId(1L)).thenReturn(List.of(pagamento));
        String fullName = pagamentoService.getClientFullNameForPolizza(1L);
        assertEquals("MarioRossi", fullName);
    }

    @Test
    void getClientFullNameForPolizza_ShouldReturnUnknownIfNoData() {
        when(pagamentoRepository.findByPolizzaId(1L)).thenReturn(List.of());
        String fullName = pagamentoService.getClientFullNameForPolizza(1L);
        assertEquals("Unknown", fullName);
    }
}
