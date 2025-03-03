package it.aesse.AESSE.converter;

import it.aesse.AESSE.converter.AbstractConverter;
import it.aesse.AESSE.dto.PolizzaDto;
import it.aesse.AESSE.model.Polizza;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PolizzaConverter extends AbstractConverter<Polizza, PolizzaDto> {
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Polizza toEntity(PolizzaDto polizzaDto) {
        return modelMapper.map(polizzaDto, Polizza.class);
    }

    @Override
    public PolizzaDto toDTO(Polizza polizza) {
        return modelMapper.map(polizza, PolizzaDto.class);
    }
}