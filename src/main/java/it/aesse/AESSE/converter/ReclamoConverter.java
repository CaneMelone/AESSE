package it.aesse.AESSE.converter;

import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.model.Reclamo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ReclamoConverter extends AbstractConverter<Reclamo, ReclamoDto>
{
    private ModelMapper modelMapper = new ModelMapper();

    //metodo per convertire da DTO a Entity
    @Override
    public Reclamo toEntity(ReclamoDto reclamoDto) {
        return modelMapper.map(reclamoDto, Reclamo.class);
    }

    //metodo per convertire da Entity a DTO
    @Override
    public ReclamoDto toDTO(Reclamo reclamo) {
        return modelMapper.map(reclamo, ReclamoDto.class);
    }
}