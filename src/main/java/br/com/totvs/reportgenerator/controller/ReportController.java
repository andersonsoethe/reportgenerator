package br.com.totvs.reportgenerator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.totvs.reportgenerator.model.Report;
import br.com.totvs.reportgenerator.repository.ReportRepository;
import br.com.totvs.reportgenerator.vo.ReportVO;

@RestController
public class ReportController {

    @Autowired
    ReportRepository reportRepository;

    @RequestMapping("/reports")
    public List<ReportVO> list(){

        List<Report> report = (List<Report>) reportRepository.findAll();
        return ReportVO.convert(report);
    }

    @RequestMapping("/findReportsByPriority")
    public List<ReportVO> listByPriority(String priority){
        List<Report> reportVOList= reportRepository.findReporByPriority(priority);
        return ReportVO.convert(reportVOList);
    }

    @RequestMapping("/findReportsByStoryPoints")
    public List<ReportVO> listByStoryPoints(String storyPoints){
        List<Report> reportVOList= reportRepository.findReporByPriority(storyPoints);
        return ReportVO.convert(reportVOList);
    }
}
