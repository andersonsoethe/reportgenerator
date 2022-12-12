package br.com.totvs.reportgenerator.vo;

import java.util.List;
import java.util.stream.Collectors;

import br.com.totvs.reportgenerator.model.Report;

public class ReportVO {

    private long id;
    private String priority;
    private String issueKey;
    private String summary;
    private String deliveryAgreementDate;
    private String storyPoints;
    private String created;
    private String resolved;
    private long daysToFinish;

    public ReportVO(Report report){
        this.id = report.getId();
        this.priority = report.getPriority();
        this.issueKey = report.getIssueKey();
        this.summary = report.getSummary();
        this.deliveryAgreementDate = report.getDeliveryAgreementDate();
        this.storyPoints = report.getStoryPoints();
        this.created = report.getCreated();
        this.resolved = report.getResolved();
        this.daysToFinish = report.getDaysToFinish();
    }


    public long getId() {
        return id;
    }

    public String getPriority() {
        return priority;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public String getSummaty() {
        return summary;
    }

    public String getDeliveryAgreementDate() {
        return deliveryAgreementDate;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public String getCreated() {
        return created;
    }

    public String getResolved() {
        return resolved;
    }

    public long getDaysToFinish() {
        return daysToFinish;
    }

    public static List<ReportVO> convert(List<Report> reports){
        return reports.stream().map(ReportVO::new).collect(Collectors.toList());
    }
}
