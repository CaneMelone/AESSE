package it.aesse.AESSE.converter;


import it.aesse.AESSE.dto.PolizzaDto;
import it.aesse.AESSE.model.Polizza;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PolizzaConverter extends AbstractConverter<Polizza, PolizzaDto>
{
    private ModelMapper modelMapper = new ModelMapper();

    //metodo per convertire da DTO a Entity
    @Override
    public Polizza toEntity(PolizzaDto polizzaDto) {
        return modelMapper.map(polizzaDto, Polizza.class);
    }

    //metodo per convertire da Entity a DTO
    @Override
    public PolizzaDto toDTO(Polizza polizza) {
        return modelMapper.map(polizza, PolizzaDto.class);
    }
}