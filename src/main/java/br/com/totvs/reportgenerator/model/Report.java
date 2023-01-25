package br.com.totvs.reportgenerator.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Integer daysToFinish;
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
    private String timeSpend;
    private Date created;
    private Date resolved;
    @Enumerated(EnumType.STRING)
    private DeliveryTimeStatusEnum deliveryTimeStatus;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIssueType() {
        return issueType;
    }

    public void setIssueType(String issueType) {
        this.issueType = issueType;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIssueKey() {
        return issueKey;
    }

    public void setIssueKey(String key) {
        this.issueKey = key;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public String getDemandProfile() {
        return demandProfile;
    }

    public void setDemandProfile(String demandProfile) {
        this.demandProfile = demandProfile;
    }

    public String getDeliveryAgreementDate() {
        return deliveryAgreementDate;
    }

    public void setDeliveryAgreementDate(String deliveryAgreementDate) {
        this.deliveryAgreementDate = deliveryAgreementDate;
    }

    public String getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(String storyPoints) {
        this.storyPoints = storyPoints;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getResolved() {
        return resolved;
    }

    public void setResolved(Date resolved) {
        this.resolved = resolved;
    }

    public String getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(String
            timeSpend) {
        this.timeSpend = timeSpend;
    }

    public DeliveryTimeStatusEnum getDeliveryTimeStatus() {
        return deliveryTimeStatus;
    }

    public void setDeliveryTimeStatus(DeliveryTimeStatusEnum deliveryTimeStatus) {
        this.deliveryTimeStatus = deliveryTimeStatus;
    }

    public Integer getDaysToFinish() {
        return daysToFinish;
    }

    public void setDaysToFinish(Integer daysToFinish) {
        this.daysToFinish = daysToFinish;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", issueType='" + issueType + '\'' +
                ", priority='" + priority + '\'' +
                ", key='" + issueKey + '\'' +
                ", summary='" + summary + '\'' +
                ", status='" + status + '\'' +
                ", ticket='" + ticket + '\'' +
                ", assignee='" + assignee + '\'' +
                ", reporter='" + reporter + '\'' +
                ", demandProfile='" + demandProfile + '\'' +
                ", deliveryAgreementDate='" + deliveryAgreementDate + '\'' +
                ", storyPoints='" + storyPoints + '\'' +
                ", created='" + created + '\'' +
                ", resolved='" + resolved + '\'' +
                ", timeSpend='" + timeSpend + '\'' +
                ", daysToFinish='" + daysToFinish + '\'' +
                ", deliveryTimeStatus='" + deliveryTimeStatus + '\'' +
                '}';
    }
}