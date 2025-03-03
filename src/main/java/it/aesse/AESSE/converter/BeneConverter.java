package it.aesse.AESSE.converter;


    import it.aesse.AESSE.dto.BeneDto;
    import it.aesse.AESSE.model.Bene;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class BeneConverter extends AbstractConverter<Bene, BeneDto> {
        private ModelMapper modelMapper = new ModelMapper();

        @Override
        public Bene toEntity(BeneDto beneDto) {
            return modelMapper.map(beneDto, Bene.class);
        }

        @Override
        public BeneDto toDTO(Bene bene) {
            return modelMapper.map(bene, BeneDto.class);
        }
    }