package br.com.totvs.reportgenerator.vo;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.totvs.reportgenerator.model.DeliveryTimeStatusEnum;
import br.com.totvs.reportgenerator.model.Report;

public class ReportVO {

    private long id;
    private String issueType;
    private String priority;
    private String issueKey;
    private String summary;
    private String status;
    private String ticket;
    private String assignee;
    private String reporter;
    private String demandProfile;
    private String deliveryAgreementDate;
    private String storyPoints;
    private Date created;
    private Date resolved;
    private String timeSpend;
    @Enumerated(EnumType.STRING)
    private DeliveryTimeStatusEnum deliveryTimeStatus;
    private Integer daysToFinish;

    public ReportVO (Report report) {
        this.id = report.getId();
        this.issueType = report.getIssueType();
        this.priority = report.getPriority();
        this.issueKey = report.getIssueKey();
        this.summary = report.getSummary();
        this.status = report.getStatus();
        this.ticket = report.getTicket();
        this.assignee = report.getAssignee();
        this.reporter = report.getReporter();
        this.demandProfile = report.getDemandProfile();
        this.deliveryAgreementDate = report.getDeliveryAgreementDate();
        this.storyPoints = report.getStoryPoints();
        this.created = report.getCreated();
        this.resolved = report.getResolved();
        this.timeSpend = report.getTimeSpend();
        this.deliveryTimeStatus = report.getDeliveryTimeStatus();
        this.daysToFinish = report.getDaysToFinish();

    }

    public long getId() {
        return id;
    }

    public String getIssueType() {
        return issueType;
    }

    public String getPriority() {
        return priority;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public String getSummary() {
        return summary;
    }

    public String getStatus() {
        return status;
    }

    public String getTicket() {
        return ticket;
    }

    public String getAssignee() {
        return assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public String getDemandProfile() {
        return demandProfile;
    }

    public String getDeliveryAgreementDate() {
        return deliveryAgreementDate;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public Date getCreated() {
        return created;
    }

    public Date getResolved() {
        return resolved;
    }

    public String getTimeSpend() {
        return timeSpend;
    }

    public DeliveryTimeStatusEnum getDeliveryTimeStatus() {
        return deliveryTimeStatus;
    }

    public Integer getDaysToFinish() {
        return daysToFinish;
    }

    public static List<ReportVO> convert(List<Report> reports){
        return reports.stream().map(ReportVO::new).collect(Collectors.toList());
    }
}
