package it.aesse.AESSE.service;

import it.aesse.AESSE.dto.ClienteDto;
import it.aesse.AESSE.model.Cliente;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.repository.ClienteRepository;
import it.aesse.AESSE.repository.PolizzaRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Classe di servizio per la gestione delle entità Cliente.
 */
@Service
public class ClienteService extends AbstractService<Cliente, ClienteDto> {

    @Autowired
    PolizzaRepository polizzaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    /**
     * Verifica se il cliente con l'ID specificato ha polizze attive.
     *
     * @param idCliente l'ID del cliente
     * @return true se il cliente ha polizze attive, altrimenti false
     */
    public boolean hasPolizzeAttive(Long idCliente) {
        List<Polizza> polizze = polizzaRepository.findByClienteId(idCliente);
        return polizze.stream().anyMatch(polizza -> "Attiva".equalsIgnoreCase(polizza.getStato()));
    }

    /**
     * Recupera le informazioni di contatto dei clienti con polizze attive.
     *
     * @return una lista di mappe contenenti le informazioni di contatto dei clienti
     */
    public List<Map<String, String>> getInformazioniDiContatto() {
        List<Cliente> clienti = clienteRepository.findAll();

        return clienti.stream()
                .filter(cliente -> hasPolizzeAttive(cliente.getId()))
                .map(cliente -> Map.of(
                        "nome", cliente.getNome() + " " + cliente.getCognome(),
                        "telefono", cliente.getTelefono(),
                        "email", cliente.getEmail()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Genera un file Excel con le informazioni del cliente specificato.
     *
     * @param cliente il cliente di cui generare il file Excel
     * @return un array di byte contenente il file Excel
     * @throws IOException se si verifica un errore durante la generazione del file
     */
    public byte[] generaExcelCliente(Cliente cliente) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Cliente");

        // Intestazione colonne
        Row headerRow = sheet.createRow(0);
        String[] colonne = {"ID", "Nome", "Cognome", "Data di Nascita", "Indirizzo", "Telefono", "Email", "Codice Fiscale", "Bersani"};
        for (int i = 0; i < colonne.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(colonne[i]);
            cell.setCellStyle(createHeaderStyle(workbook));
        }

        // Dati cliente
        Row row = sheet.createRow(1);
        row.createCell(0).setCellValue(cliente.getId());
        row.createCell(1).setCellValue(cliente.getNome());
        row.createCell(2).setCellValue(cliente.getCognome());
        row.createCell(3).setCellValue(cliente.getData_nascita().toString());
        row.createCell(4).setCellValue(cliente.getIndirizzo());
        row.createCell(5).setCellValue(cliente.getTelefono());
        row.createCell(6).setCellValue(cliente.getEmail());
        row.createCell(7).setCellValue(cliente.getCodice_fiscale());
        row.createCell(8).setCellValue(cliente.getBersani() ? "Sì" : "No");

        // Auto-resize colonne
        for (int i = 0; i < colonne.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Scrittura su byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();
        return outputStream.toByteArray();
    }

    /**
     * Crea uno stile per l'intestazione delle colonne del file Excel.
     *
     * @param workbook il workbook in cui creare lo stile
     * @return lo stile creato
     */
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        return style;
    }
}