package it.aesse.AESSE.service;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
@Service
public interface ServiceDTO<DTO> {

    public List<DTO> getAll();

    public DTO read(long id);

    public DTO insert(DTO dto);

    public DTO update(DTO dto);

    public void delete(long id);

    public Collection<DTO> readAll();
}
