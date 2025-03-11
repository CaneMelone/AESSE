package it.aesse.AESSE.converter;



import java.util.ArrayList;
import java.util.List;

// Classe astratta che implementa l'interfaccia Converter per la conversione di entità e DTO
public abstract class AbstractConverter<Entity,DTO> implements Converter<Entity,DTO>
{
    // Metodo che converte una lista di DTO in una lista di entità
    public List<Entity> toEntityList(Iterable<DTO> listDTO)
    {
        // Crea una lista vuota per le entità
        List<Entity> list = new ArrayList<Entity>();

        // Verifica se la lista di DTO non è nulla
        if (listDTO != null)
        {
            // Itera su ciascun DTO della lista e lo converte in un'entità
            for (DTO dto : listDTO)
            {
                // Converte il DTO in un'entità utilizzando il metodo astratto
                Entity entity = toEntity(dto);
                list.add(entity);
            }
        }
        return list;
    }

    // Metodo che converte una lista di entità in una lista di DTO
    public List<DTO> toDTOList(Iterable<Entity> listEntity)
    {
        List<DTO> list = new ArrayList<DTO>();

        if (listEntity != null)
        {
            //scorri le entità e le converte in DTO
            for (Entity entity : listEntity)
            {
                DTO dto = toDTO(entity);
                list.add(dto);
            }
        }
        return list;
    }
}