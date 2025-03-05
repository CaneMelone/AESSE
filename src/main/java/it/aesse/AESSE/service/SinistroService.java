package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.SinistroDto;
import it.aesse.AESSE.model.Sinistro;
import it.aesse.AESSE.repository.SinistroRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class SinistroService extends AbstractService<Sinistro, SinistroDto> {

    @Autowired
    private SinistroRepository sinistroRepository;

    public List<SinistroDto> getSinistriByPolizza(Long polizzaId) {
        List<Sinistro> sinistri = sinistroRepository.findByPolizzaId(polizzaId);
        return converter.toDTOList(sinistri);
    }

    public List<SinistroDto> getSinistriByStato(String stato) {
        return converter.toDTOList(sinistroRepository.findByStato(stato));
    }

    public List<SinistroDto> getSinistriByCliente(Long clienteId) {
        List<Sinistro> sinistri = sinistroRepository.findByClienteId(clienteId);
        return converter.toDTOList(sinistri);
    }
}
