package br.com.totvs.reportgenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.totvs.reportgenerator.model.DeliveryTimeStatusEnum;
import br.com.totvs.reportgenerator.model.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.deliveryTimeStatus = :#{#deliveryTimeStatus} ORDER BY r.resolved DESC,"
            + " r.created DESC")
    List<Report> getAllReports(@Param("deliveryTimeStatus") DeliveryTimeStatusEnum deliveryTimeStatus);

    @Query("SELECT r FROM Report r ORDER BY r.resolved DESC, r.created DESC")
    List<Report> getAllReports();

    Report findByIssueKey(String issueKey);
}
