package it.aesse.AESSE.controller;

import it.aesse.AESSE.service.ServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;


@Controller
public abstract class AbstractController <DTO> {

    @Autowired
    private ServiceDTO<DTO> serviceDTO;


    @DeleteMapping("/delete")
    public void delete(@RequestParam("id") Long id) {
        serviceDTO.delete(id);
    }


    @PutMapping("/update")
    public DTO update(@RequestBody DTO dto) {
        serviceDTO.update(dto);
        return dto;
    }

    @PostMapping("/insert")
    public DTO insert(@RequestBody DTO dto) {
        serviceDTO.insert(dto);
        return dto;
    }

    @GetMapping("/read")
    public DTO read(@RequestParam("id") Long id) {
        return serviceDTO.read(id);
    }


    @GetMapping("/readAll")
    public Collection<DTO>  readAll() {
        return serviceDTO.readAll();
    }

}