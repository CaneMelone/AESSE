package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.model.Bene;
import it.aesse.AESSE.sub.PolizzaBene;
import it.aesse.AESSE.repository.PolizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.aesse.AESSE.repository.BeneRepository;

import java.util.List;

@Service
public class BeneService extends AbstractService<Bene, BeneDto> {
    @Autowired
    private BeneRepository repository;

    @Autowired
    private PolizzaRepository polizzaRepository;

    public List<PolizzaBene> findBeniByClienteId(Long clienteId) {
        return polizzaRepository.findByCliente_Id(clienteId);
    }
}
