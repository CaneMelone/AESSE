package it.aesse.AESSE.converter;

import it.aesse.AESSE.dto.PagamentoDto;
import it.aesse.AESSE.model.Pagamento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagamentoConverter extends AbstractConverter<Pagamento, PagamentoDto>
{
    private ModelMapper modelMapper = new ModelMapper();

    //metodo per convertire da DTO a Entity
    @Override
    public Pagamento toEntity(PagamentoDto pagamentoDto) {
        return modelMapper.map(pagamentoDto, Pagamento.class);
    }

    //metodo per convertire da Entity a DTO
    @Override
    public PagamentoDto toDTO(Pagamento pagamento) {
        return modelMapper.map(pagamento, PagamentoDto.class);
    }
}