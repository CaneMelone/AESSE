package it.aesse.AESSE.converter;



import java.util.ArrayList;
import java.util.List;

// Classe astratta che implementa l'interfaccia Converter per la conversione di entità e DTO
public abstract class AbstractConverter<Entity,DTO> implements Converter<Entity,DTO> {

    // Metodo che converte una lista di DTO in una lista di entità
    public List<Entity> toEntityList(Iterable<DTO> listDTO) {
        // Crea una lista vuota per le entità
        List<Entity> list = new ArrayList<Entity>();

        // Verifica se la lista di DTO non è nulla
        if (listDTO != null) {
            // Itera su ciascun DTO della lista e lo converte in un'entità
            for (DTO dto : listDTO) {
                // Converte il DTO in un'entità utilizzando il metodo astratto
                Entity entity = toEntity(dto);
                // Aggiunge l'entità alla lista
                list.add(entity);
            }
        }
        // Restituisce la lista di entità convertite
        return list;
    }

    // Metodo che converte una lista di entità in una lista di DTO
    public List<DTO> toDTOList(Iterable<Entity> listEntity) {
        // Crea una lista vuota per i DTO
        List<DTO> list = new ArrayList<DTO>();

        // Verifica se la lista di entità non è nulla
        if (listEntity != null) {
            // Itera su ciascuna entità della lista e la converte in un DTO
            for (Entity entity : listEntity) {
                // Converte l'entità in un DTO utilizzando il metodo astratto
                DTO dto = toDTO(entity);
                // Aggiunge il DTO alla lista
                list.add(dto);
            }
        }
        // Restituisce la lista di DTO convertiti
        return list;
    }
}
