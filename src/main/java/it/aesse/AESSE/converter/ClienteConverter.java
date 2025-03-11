package it.aesse.AESSE.converter;

import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ClienteConverter extends AbstractConverter<Cliente, ClienteDto>
{
    private ModelMapper modelMapper = new ModelMapper();

    //metodo per convertire da DTO a Entity
    @Override
    public Cliente toEntity(ClienteDto clienteDto) {
        return modelMapper.map(clienteDto, Cliente.class);
    }

    //metodo per convertire da Entity a DTO
    @Override
    public ClienteDto toDTO(Cliente cliente) {
        return modelMapper.map(cliente, ClienteDto.class);
    }
}