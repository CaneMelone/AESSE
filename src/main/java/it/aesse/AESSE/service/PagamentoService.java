package it.aesse.AESSE.service;

import it.aesse.AESSE.converter.PagamentoConverter;
import it.aesse.AESSE.dto.PagamentoDto;
import it.aesse.AESSE.model.Pagamento;
import it.aesse.AESSE.model.Polizza;
import it.aesse.AESSE.repository.PagamentoRepository;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Classe di servizio per la gestione delle entità Pagamento.
 */
@Slf4j
@Service
public class PagamentoService extends AbstractService<Pagamento, PagamentoDto>
{
    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private PagamentoConverter converter;

    /**
     * Recupera una lista di PagamentoDto per il dato polizzaId.
     *
     * @param polizzaId l'ID della polizza
     * @return una lista di PagamentoDto associati al dato polizzaId
     */
    public List<PagamentoDto> getPagamentiByPolizza(Long polizzaId)
    {
        List<Pagamento> pagamenti = pagamentoRepository.findByPolizzaId(polizzaId);
        return converter.toDTOList(pagamenti);
    }

    /**
     * Recupera una lista di PagamentoDto per il dato metodo di pagamento.
     *
     * @param metodo il metodo di pagamento
     * @return una lista di PagamentoDto associati al dato metodo di pagamento
     */
    public List<PagamentoDto> getPagamentiByMetodo(String metodo)
    {
        List<Pagamento> pagamenti = pagamentoRepository.findByMetodo(metodo);
        return converter.toDTOList(pagamenti);
    }

    /**
     * Calcola la somma dei pagamenti per il dato polizzaId.
     *
     * @param polizzaId l'ID della polizza
     * @return la somma dei pagamenti per il dato polizzaId
     */
    public BigDecimal getSommaPagamentiPolizza(Long polizzaId)
    {
        List<Pagamento> pagamenti = pagamentoRepository.findByPolizzaId(polizzaId);
        return pagamenti.stream()
                .map(Pagamento::getImporto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Calcola la somma dei pagamenti per il dato polizzaId e anno.
     *
     * @param polizzaId l'ID della polizza
     * @param year      l'anno per filtrare i pagamenti
     * @return la somma dei pagamenti per il dato polizzaId e anno
     */
    public BigDecimal getSommaPagamentiByPolizzaAndYear(Long polizzaId, int year)
    {
        List<Pagamento> pagamenti = pagamentoRepository.findByPolizzaId(polizzaId);
        return pagamenti.stream()
                .filter(p -> p.getData_pagamento() != null && p.getData_pagamento().getYear() == year)
                .map(Pagamento::getImporto)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * Recupera il nome completo del cliente associato al dato polizzaId.
     *
     * @param polizzaId l'ID della polizza
     * @return il nome completo del cliente associato al dato polizzaId, o "Unknown" se non trovato
     */
    public String getClientFullNameForPolizza(Long polizzaId)
    {
        List<Pagamento> list = pagamentoRepository.findByPolizzaId(polizzaId);
        if (!list.isEmpty() && list.get(0).getPolizza() != null && list.get(0).getPolizza().getCliente() != null)
        {
            var cliente = list.get(0).getPolizza().getCliente();
            return cliente.getNome() + cliente.getCognome();
        }
        return "Unknown";
    }

    /**
     * Genera un file Excel con i dettagli dei pagamenti per il dato polizzaId.
     *
     * @param polizzaId l'ID della polizza
     * @return un array di byte contenente il file Excel
     * @throws IOException se si verifica un errore durante la generazione del file
     */
    public byte[] generateExcelByPolizza(Long polizzaId) throws IOException
    {
        List<Pagamento> pagamenti = pagamentoRepository.findByPolizzaId(polizzaId);
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("PagamentiByPolizza" + polizzaId);

        int currentRow = 0;
        XSSFRow row0 = sheet.createRow(currentRow++);
        row0.createCell(0).setCellValue("Cliente");
        XSSFRow row1 = sheet.createRow(currentRow++);
        row1.createCell(0).setCellValue("ID");
        row1.createCell(1).setCellValue("Nome");
        row1.createCell(2).setCellValue("Cognome");
        row1.createCell(3).setCellValue("Email");
        XSSFRow row2 = sheet.createRow(currentRow++);
        if (!pagamenti.isEmpty() && pagamenti.get(0).getPolizza() != null &&
                pagamenti.get(0).getPolizza().getCliente() != null)
        {
            var cliente = pagamenti.get(0).getPolizza().getCliente();
            row2.createCell(0).setCellValue(cliente.getId());
            row2.createCell(1).setCellValue(cliente.getNome());
            row2.createCell(2).setCellValue(cliente.getCognome());
            row2.createCell(3).setCellValue(cliente.getEmail());
        }
        currentRow++;
        XSSFRow row4 = sheet.createRow(currentRow++);
        row4.createCell(0).setCellValue("Polizza");
        XSSFRow row5 = sheet.createRow(currentRow++);
        row5.createCell(0).setCellValue("ID");
        row5.createCell(1).setCellValue("Tipo");
        row5.createCell(2).setCellValue("Stato");
        row5.createCell(3).setCellValue("Premio");
        XSSFRow row6 = sheet.createRow(currentRow++);
        if (!pagamenti.isEmpty() && pagamenti.get(0).getPolizza() != null)
        {
            Polizza polizza = pagamenti.get(0).getPolizza();
            row6.createCell(0).setCellValue(polizza.getId_polizza());
            row6.createCell(1).setCellValue(polizza.getTipo());
            row6.createCell(2).setCellValue(polizza.getStato());
            BigDecimal premio = polizza.getPremio();
            row6.createCell(3).setCellValue(premio != null ? premio.doubleValue() : 0);
        }
        currentRow++;
        XSSFRow row8 = sheet.createRow(currentRow++);
        row8.createCell(0).setCellValue("Pagamenti");
        XSSFRow row9 = sheet.createRow(currentRow++);
        row9.createCell(0).setCellValue("ID");
        row9.createCell(1).setCellValue("Data");
        row9.createCell(2).setCellValue("Importo");
        row9.createCell(3).setCellValue("Metodo");
        row9.createCell(4).setCellValue("Causale");
        for (Pagamento p : pagamenti)
        {
            XSSFRow row = sheet.createRow(currentRow++);
            row.createCell(0).setCellValue(p.getId_pagamento());
            row.createCell(1).setCellValue(p.getData_pagamento() != null ? p.getData_pagamento().toString() : "");
            BigDecimal importo = p.getImporto();
            row.createCell(2).setCellValue(importo != null ? importo.doubleValue() : 0);
            row.createCell(3).setCellValue(p.getMetodo() != null ? p.getMetodo() : "");
            row.createCell(4).setCellValue(p.getCausale() != null ? p.getCausale() : "");
        }
        for (int i = 0; i < 5; i++)
        {
            sheet.autoSizeColumn(i);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        workbook.write(bos);
        workbook.close();
        return bos.toByteArray();
    }
}