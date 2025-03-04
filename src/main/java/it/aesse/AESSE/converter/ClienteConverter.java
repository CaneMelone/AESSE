package it.aesse.AESSE.converter;


import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.model.Bene;
import it.aesse.AESSE.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDto> {
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Cliente toEntity(ClienteDto clienteDto) {
        return modelMapper.map(clienteDto, Cliente.class);
    }

    @Override
    public ClienteDto toDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDto.class);
    }
}