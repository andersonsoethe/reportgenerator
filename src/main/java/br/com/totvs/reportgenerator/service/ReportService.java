package br.com.totvs.reportgenerator.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.Iterator;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.totvs.reportgenerator.model.Report;
import br.com.totvs.reportgenerator.repository.ReportRepository;
import br.com.totvs.reportgenerator.util.DateUtil;

@Service
public class ReportService {

    private final ReportRepository reportRepository;

    @Autowired
    DateUtil dateUtil;

    public ReportService(ReportRepository reportRepository){
            this.reportRepository = reportRepository;
    }

    @Transactional(TxType.REQUIRED)
    public void readFile(byte[] bytes) {
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
                Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            save(sheet);
        } catch (IOException e){
            System.out.println("Erro ao ler o arquivo"+e);
        }
    }

    private void save(Sheet sheet) {
        try {
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getRowNum() < 4 || !rowIterator.hasNext()) {
                    continue;
                }

                Report report = new Report();

                int cellNumber = 0;

                report.setIssueType(row.getCell(cellNumber++).getStringCellValue());
                report.setPriority(row.getCell(cellNumber++).getStringCellValue());
                report.setIssueKey(row.getCell(cellNumber++).getStringCellValue());
                report.setSummary(row.getCell(cellNumber++).getStringCellValue());
                //Dia da abertura
                report.setCreated(String.valueOf(row.getCell(cellNumber++).getDateCellValue()));
                report.setStatus(row.getCell(cellNumber++).getStringCellValue());
                report.setTicket(row.getCell(cellNumber++).getStringCellValue());
                report.setAssignee(row.getCell(cellNumber++).getStringCellValue());
                report.setReporter(row.getCell(cellNumber++).getStringCellValue());
                //Dia da entrega
                if(row.getCell(cellNumber).getDateCellValue() == null) {
                    report.setResolved(dateUtil.getDateNow());
                } else {
                    report.setResolved((String.valueOf(row.getCell(cellNumber++).getDateCellValue())));
                }
                report.setTimeSpend(String.valueOf(row.getCell(cellNumber++).getNumericCellValue()));
                report.setDemandProfile(row.getCell(cellNumber++).getStringCellValue());
                report.setDeliveryAgreementDate(row.getCell(cellNumber++).getStringCellValue());
                report.setStoryPoints(String.valueOf(row.getCell(cellNumber).getNumericCellValue()));
                report.setDaysToFinish(dateUtil.calcDaysToFinishTask(report.getCreated(), report.getResolved()));
                if (report.getStatus() == "Concluído"){

                }
                reportRepository.save(report);
                //Calcular o Status
                System.out.println(report.getIssueKey()+" | "+report.getDaysToFinish());
            }
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar no banco "+ "Error: "+e);
        }

    }

}
