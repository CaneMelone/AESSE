package it.aesse.AESSE.converter;

    import it.aesse.AESSE.converter.AbstractConverter;
    import it.aesse.AESSE.dto.SinistroDto;
    import it.aesse.AESSE.model.Sinistro;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class SinistroConverter extends AbstractConverter<Sinistro, SinistroDto> {
        private ModelMapper modelMapper = new ModelMapper();

        @Override
        public Sinistro toEntity(SinistroDto sinistroDto) {
            return modelMapper.map(sinistroDto, Sinistro.class);
        }

        @Override
        public SinistroDto toDTO(Sinistro sinistro) {
            return modelMapper.map(sinistro, SinistroDto.class);
        }
    }