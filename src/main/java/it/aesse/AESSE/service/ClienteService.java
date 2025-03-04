package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.repository.ClienteRepository;
import it.aesse.AESSE.repository.PolizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClienteService extends AbstractService<Cliente, ClienteDto>{

    @Autowired
    PolizzaRepository polizzaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    /*public boolean hasPolizzeAttve(Long idCliente) {
        List<Polizza> polizze = polizzaRepository.findByClienteId(idCliente);
        return polizze.stream().anyMatch(polizza -> "Attiva".equalsIgnoreCase(polizza.getStato()));
    }

    public List<Map<String, String>> getInformazioniDiContatto() {
        List<Cliente> clienti = clienteRepository.findAll();

        return clienti.stream()
                .filter(cliente -> hasPolizzeAttve(cliente.getIdCliente()))
                .map(cliente -> Map.of(
                        "nome", cliente.getNome() + " " + cliente.getCognome(),
                        "telefono", cliente.getTelefono(),
                        "email", cliente.getEmail()
                ))
                .collect(Collectors.toList());
    }*/

}
