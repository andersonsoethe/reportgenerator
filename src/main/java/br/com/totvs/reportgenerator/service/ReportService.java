package br.com.totvs.reportgenerator.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.totvs.reportgenerator.model.DeliveryTimeStatusEnum;
import br.com.totvs.reportgenerator.model.Report;
import br.com.totvs.reportgenerator.repository.ReportRepository;
import br.com.totvs.reportgenerator.util.DateUtil;
import br.com.totvs.reportgenerator.vo.ReportVO;

@Service
public class ReportService {
    private static final String CONCLUIDO = "Concluído";
    private static final String CANCELADO = "Cancelado";

    private final ReportRepository reportRepository;
    private final DateUtil dateUtil;

    public ReportService(ReportRepository reportRepository, DateUtil dateUtil) {
        this.reportRepository = reportRepository;
        this.dateUtil = dateUtil;
    }

    @Transactional(TxType.REQUIRED)
    @CacheEvict(value = "reportsInTime", allEntries = true)
    public void readFile(byte[] bytes) throws IOException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
                Workbook workbook = WorkbookFactory.create(is)) {
            Sheet sheet = workbook.getSheetAt(0);
            save(sheet);
        }
    }

    public Page<ReportVO> findPaginated(Pageable pageable, DeliveryTimeStatusEnum deliveryTimeStatusFilter) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<ReportVO> list;
        List<ReportVO> reports = getAllReports(deliveryTimeStatusFilter);

        if (reports.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, reports.size());
            list = reports.subList(startItem, toIndex);
        }

        Page<ReportVO> reportPage
                = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), reports.size());

        return reportPage;
    }

    private List<ReportVO> getAllReports(DeliveryTimeStatusEnum deliveryStatusFilter) {
        if (deliveryStatusFilter == null) {
            return ReportVO.convert(reportRepository.getAllReports());
        }
        return ReportVO.convert(reportRepository.getAllReports(deliveryStatusFilter));
    }

    private void save(Sheet sheet) {
        try {
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                if (row.getRowNum() < 4 || !rowIterator.hasNext()) {
                    continue;
                }

                Report report = reportRepository.findByIssueKey(row.getCell(2).getStringCellValue());
                if (report == null) {
                    report = new Report();
                }

                int cellNumber = 0;

                report.setIssueType(row.getCell(cellNumber++).getStringCellValue());
                report.setPriority(row.getCell(cellNumber++).getStringCellValue());
                report.setIssueKey(row.getCell(cellNumber++).getStringCellValue());
                report.setSummary(row.getCell(cellNumber++).getStringCellValue());
                //Dia da abertura
                report.setCreated(row.getCell(cellNumber++).getDateCellValue());
                report.setStatus(row.getCell(cellNumber++).getStringCellValue());
                report.setTicket(String.valueOf(row.getCell(cellNumber++).getNumericCellValue()));
                report.setAssignee(row.getCell(cellNumber++).getStringCellValue());
                report.setReporter(row.getCell(cellNumber++).getStringCellValue());
                //Dia da entrega
                report.setResolved(row.getCell(cellNumber++).getDateCellValue());
                report.setTimeSpend(String.valueOf(row.getCell(cellNumber++).getNumericCellValue()));
                report.setDemandProfile(row.getCell(cellNumber++).getStringCellValue());
                report.setDeliveryAgreementDate(row.getCell(cellNumber++).getStringCellValue());
                report.setStoryPoints(String.valueOf(row.getCell(cellNumber).getNumericCellValue()));
                if (report.getResolved() != null) {
                    report.setDaysToFinish(dateUtil.getDaysToFinishTask(report.getCreated(), report.getResolved()));
                }
                //Calcular o Status
                report.setDeliveryTimeStatus(getDeliveryTimeStatus(report));
                reportRepository.save(report);
                System.out.println(report.getIssueKey()+" | "+report.getDaysToFinish());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private DeliveryTimeStatusEnum getDeliveryTimeStatus(Report report) {
        if (CONCLUIDO.equalsIgnoreCase(report.getStatus())) {
            double goalTime = Double.parseDouble(report.getStoryPoints());
            double realTime = Double.parseDouble(report.getTimeSpend());

            if (realTime > goalTime) {
                return DeliveryTimeStatusEnum.LATE;
            }

            return DeliveryTimeStatusEnum.IN_TIME;
        } else if (CANCELADO.equalsIgnoreCase(report.getStatus())) {
            return DeliveryTimeStatusEnum.CANCELED;
        }

        return DeliveryTimeStatusEnum.IN_PROGRESS;
    }
}
