package it.aesse.AESSE.controller;

import it.aesse.AESSE.service.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;


@RestController
public abstract class AbstractController <DTO>
{
    @Autowired
    private ServiceDTO<DTO> serviceDTO;

    // CRUD
    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        serviceDTO.delete(id);
    }

    // CRUD
    @PutMapping("/update")
    public DTO update(@RequestBody DTO dto)
    {
        serviceDTO.update(dto);
        return dto;
    }

    // CRUD
    @PostMapping("/insert")
    public DTO insert(@RequestBody DTO dto)
    {
        serviceDTO.insert(dto);
        return dto;
    }

    // CRUD
    @GetMapping("/read")
    public DTO read(@RequestParam("id") Long id) {
        return serviceDTO.read(id);
    }

    // CRUD
    @GetMapping("/readAll")
    public Collection<DTO>  readAll() {
        return serviceDTO.readAll();
    }

    // CRUD
    @PatchMapping("/patch")
    public DTO patch(@RequestParam("id") Long id,
                     @RequestBody Map<String, Object> changes)
    {
        return serviceDTO.patch(id, changes);
    }
}