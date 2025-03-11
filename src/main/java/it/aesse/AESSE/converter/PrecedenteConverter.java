package it.aesse.AESSE.converter;


import it.aesse.AESSE.dto.PrecedenteDto;
import it.aesse.AESSE.model.Precedente;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PrecedenteConverter extends AbstractConverter<Precedente, PrecedenteDto>
{
    private ModelMapper modelMapper = new ModelMapper();

    //metodo per convertire da DTO a Entity
    @Override
    public Precedente toEntity(PrecedenteDto precedenteDto) {
        return modelMapper.map(precedenteDto, Precedente.class);
    }

    //metodo per convertire da Entity a DTO
    @Override
    public PrecedenteDto toDTO(Precedente precedente) {
        return modelMapper.map(precedente, PrecedenteDto.class);
    }
}