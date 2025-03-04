package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.SinistroDto;
import it.aesse.AESSE.model.Sinistro;
import it.aesse.AESSE.repository.SinistroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<SinistroDto> getSinistriByCliente(Long idCliente) {
        List<Sinistro> sinistri = sinistroRepository.findByClienteId(idCliente);
        return converter.toDTOList(sinistri);
    }
}
