package it.aesse.AESSE.converter;


import it.aesse.AESSE.dto.BeneDto;
import it.aesse.AESSE.model.Bene;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BeneConverter extends AbstractConverter<Bene, BeneDto>
{
    private ModelMapper modelMapper = new ModelMapper();

    //metodo per convertire da DTO a Entity
    @Override
    public Bene toEntity(BeneDto beneDto)
    {
            return modelMapper.map(beneDto, Bene.class);
    }

    //metodo per convertire da Entity a DTO
    @Override
    public BeneDto toDTO(Bene bene) {
            return modelMapper.map(bene, BeneDto.class);
        }
}