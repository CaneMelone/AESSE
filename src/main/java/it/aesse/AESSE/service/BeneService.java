package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.model.Bene;
import it.aesse.AESSE.sub.PolizzaBene;
import it.aesse.AESSE.repository.PolizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.aesse.AESSE.repository.BeneRepository;

import java.util.List;
import java.util.stream.Collectors;

/*
 * Classe di servizio per la gestione delle entità Bene.
 */
@Service
public class BeneService extends AbstractService<Bene, BeneDto>
{
    @Autowired
    private BeneRepository repository;

    @Autowired
    private PolizzaRepository polizzaRepository;

    /**
     * Trova una lista di PolizzaBene per il cliente con l'ID specificato.
     *
     * @param clienteId l'ID del cliente
     * @return una lista di PolizzaBene associati al cliente con l'ID specificato
     */
    public List<BeneDto> findBeniByClienteId(Long clienteId)
    {
        return polizzaRepository.findByCliente_Id(clienteId).stream()
                .map(x -> converter.toDTO(x.getBene())).collect(Collectors.toList());
    }

    /**
     * Conta il numero di polizze associate al bene con l'ID specificato.
     *
     * @param beneId l'ID del bene
     * @return il numero di polizze associate al bene con l'ID specificato
     */
    public int countPoliciesForBene(Long beneId) {
        return polizzaRepository.countByBeneId(beneId);
    }
}