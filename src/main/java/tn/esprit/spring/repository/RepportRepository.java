package tn.esprit.spring.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entities.Report;


@Repository
public interface RepportRepository extends CrudRepository<Report, Integer> {
	
	@Query
	("SELECT r From Report r Where r.dateReport BETWEEN :date1 AND :date2 ORDER BY r.dateReport")
	List<Report> ListReportOfweekAgo(@Param("date1") Date date1,@Param("date2") Date date2);

}
