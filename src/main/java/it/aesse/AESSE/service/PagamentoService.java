package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.PagamentoDto;
import it.aesse.AESSE.model.Pagamento;
import it.aesse.AESSE.repository.PagamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class PagamentoService extends AbstractService<Pagamento, PagamentoDto> {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    public List<PagamentoDto> getPagamentiByPolizza(Long polizzaId) {
        List<Pagamento> pagamenti = pagamentoRepository.findByPolizzaId(polizzaId);
        return converter.toDTOList(pagamenti);
    }

    public List<PagamentoDto> getPagamentiByMetodo(String metodo) {
        List<Pagamento> pagamenti = pagamentoRepository.findByMetodo(metodo);
        return converter.toDTOList(pagamenti);
    }

    public BigDecimal getSommaPagamentiPolizza(Long polizzaId) {
        List<Pagamento> pagamenti = pagamentoRepository.findByPolizzaId(polizzaId);
        return pagamenti.stream()
                .map(Pagamento::getImporto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}