// SinistroService.java
            package it.aesse.AESSE.service;

            import it.aesse.AESSE.converter.SinistroConverter;
            import it.aesse.AESSE.dto.SinistroDto;
            import it.aesse.AESSE.model.Polizza;
            import it.aesse.AESSE.model.Sinistro;
            import it.aesse.AESSE.repository.SinistroRepository;
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

            @Slf4j
            @Service
            public class SinistroService extends AbstractService<Sinistro, SinistroDto> {

                @Autowired
                private SinistroRepository sinistroRepository;

                @Autowired
                private SinistroConverter converter;

                public List<SinistroDto> getSinistriByPolizza(Long polizzaId) {
                    List<Sinistro> sinistri = sinistroRepository.findByPolizzaId(polizzaId);
                    return converter.toDTOList(sinistri);
                }

                public List<SinistroDto> getSinistriByStato(String stato) {
                    return converter.toDTOList(sinistroRepository.findByStato(stato));
                }

                public List<SinistroDto> getSinistriByCliente(Long clienteId) {
                    List<Sinistro> sinistri = sinistroRepository.findByClienteId(clienteId);
                    return converter.toDTOList(sinistri);
                }

                public BigDecimal getSommaDanniByPolizza(Long polizzaId) {
                    List<Sinistro> sinistri = sinistroRepository.findByPolizzaId(polizzaId);
                    return sinistri.stream()
                            .map(Sinistro::getValoreDanno)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                }

                public BigDecimal getSommaConcessiByPolizza(Long polizzaId) {
                    List<Sinistro> sinistri = sinistroRepository.findByPolizzaId(polizzaId);
                    return sinistri.stream()
                            .map(Sinistro::getImportoConcesso)
                            .reduce(BigDecimal.ZERO, BigDecimal::add);
                }

                public String getClientFullNameForPolizza(Long polizzaId) {
                    List<Sinistro> list = sinistroRepository.findByPolizzaId(polizzaId);
                    if (!list.isEmpty() && list.get(0).getPolizza() != null && list.get(0).getPolizza().getCliente() != null) {
                        var cliente = list.get(0).getPolizza().getCliente();
                        return cliente.getNome() + " " + cliente.getCognome();
                    }
                    return "Unknown";
                }

                public byte[] generateExcelByPolizza(Long polizzaId) throws IOException {
                    List<Sinistro> sinistri = sinistroRepository.findByPolizzaId(polizzaId);
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("SinistriByPolizza" + polizzaId);

                    int currentRow = 0;
                    XSSFRow row0 = sheet.createRow(currentRow++);
                    row0.createCell(0).setCellValue("Cliente");
                    XSSFRow row1 = sheet.createRow(currentRow++);
                    row1.createCell(0).setCellValue("ID");
                    row1.createCell(1).setCellValue("Nome");
                    row1.createCell(2).setCellValue("Cognome");
                    row1.createCell(3).setCellValue("Email");
                    XSSFRow row2 = sheet.createRow(currentRow++);
                    if (!sinistri.isEmpty() && sinistri.get(0).getPolizza() != null &&
                            sinistri.get(0).getPolizza().getCliente() != null) {
                        var cliente = sinistri.get(0).getPolizza().getCliente();
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
                    if (!sinistri.isEmpty() && sinistri.get(0).getPolizza() != null) {
                        Polizza polizza = sinistri.get(0).getPolizza();
                        row6.createCell(0).setCellValue(polizza.getId_polizza());
                        row6.createCell(1).setCellValue(polizza.getTipo());
                        row6.createCell(2).setCellValue(polizza.getStato());
                        BigDecimal premio = polizza.getPremio();
                        row6.createCell(3).setCellValue(premio != null ? premio.doubleValue() : 0);
                    }
                    currentRow++;
                    XSSFRow row8 = sheet.createRow(currentRow++);
                    row8.createCell(0).setCellValue("Sinistri");
                    XSSFRow row9 = sheet.createRow(currentRow++);
                    row9.createCell(0).setCellValue("ID");
                    row9.createCell(1).setCellValue("Data");
                    row9.createCell(2).setCellValue("Descrizione");
                    row9.createCell(3).setCellValue("Stato");
                    row9.createCell(4).setCellValue("Valore Danno");
                    row9.createCell(5).setCellValue("Importo Concesso");
                    for (Sinistro s : sinistri) {
                        XSSFRow row = sheet.createRow(currentRow++);
                        row.createCell(0).setCellValue(s.getId_sinistro());
                        row.createCell(1).setCellValue(s.getData() != null ? s.getData().toString() : "");
                        row.createCell(2).setCellValue(s.getDescrizione() != null ? s.getDescrizione() : "");
                        row.createCell(3).setCellValue(s.getStato() != null ? s.getStato() : "");
                        BigDecimal valoreDanno = s.getValoreDanno();
                        row.createCell(4).setCellValue(valoreDanno != null ? valoreDanno.doubleValue() : 0);
                        BigDecimal importoConcesso = s.getImportoConcesso();
                        row.createCell(5).setCellValue(importoConcesso != null ? importoConcesso.doubleValue() : 0);
                    }
                    for (int i = 0; i < 6; i++) {sheet.autoSizeColumn(i);}
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    workbook.write(bos);
                    workbook.close();
                    return bos.toByteArray();
                }
            }