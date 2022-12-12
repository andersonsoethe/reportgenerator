package br.com.totvs.reportgenerator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.totvs.reportgenerator.model.Report;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {


    @Query("SELECT r from Report r WHERE r.priority = :priority")
    List<Report> findReporByPriority(String priority);

    @Query("SELECT r from Report r WHERE r.storyPoints = :storyPoints")
    List<Report> findReporByStoryPoints(String storyPoints);

}
