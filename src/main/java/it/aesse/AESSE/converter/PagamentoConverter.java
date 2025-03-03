import it.aesse.AESSE.converter.AbstractConverter;
    import it.aesse.AESSE.dto.PagamentoDto;
    import it.aesse.AESSE.model.Pagamento;
    import org.modelmapper.ModelMapper;
    import org.springframework.stereotype.Component;

    @Component
    public class PagamentoConverter extends AbstractConverter<Pagamento, PagamentoDto> {
        private ModelMapper modelMapper = new ModelMapper();

        @Override
        public Pagamento toEntity(PagamentoDto pagamentoDto) {
            return modelMapper.map(pagamentoDto, Pagamento.class);
        }

        @Override
        public PagamentoDto toDTO(Pagamento pagamento) {
            return modelMapper.map(pagamento, PagamentoDto.class);
        }
    }