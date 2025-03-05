package it.aesse.AESSE;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import it.aesse.AESSE.converter.Converter;
import it.aesse.AESSE.dto.ReclamoDto;
import it.aesse.AESSE.model.Reclamo;
import it.aesse.AESSE.repository.ReclamoRepository;
import it.aesse.AESSE.service.ReclamoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class ReclamoServiceTest {

    @Mock
    private ReclamoRepository reclamoRepository;

    @Mock
    private Converter<Reclamo, ReclamoDto> converter;

    @InjectMocks
    private ReclamoService reclamoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }




}