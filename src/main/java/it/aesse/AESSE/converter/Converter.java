package it.aesse.AESSE.converter;


import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface Converter<Entity, DTO>
{
    //metodo per convertire da DTO a Entity
    public Entity toEntity(DTO dto);

    //metodo per convertire da Entity a DTO
    public DTO toDTO(Entity entity);

    //metodo per convertire una lista di DTO in una lista di Entity
    public List<DTO> toDTOList(Iterable<Entity> entityList);

    //metodo per convertire una lista di Entity in una lista di DTO
    public List<Entity> toEntityList(Iterable<DTO> entityList);
}