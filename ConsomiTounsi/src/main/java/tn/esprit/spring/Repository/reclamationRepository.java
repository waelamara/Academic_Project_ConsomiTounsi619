package tn.esprit.spring.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.reclamation;



public interface reclamationRepository extends JpaRepository <reclamation, Long> {
	
	///raed
	@Query(value = "SELECT r.etat, r.date_rec,COUNT(r.id) AS NBB FROM reclamation r WHERE CAST(r.date_rec AS DATE)=DATE(NOW( ))-?1 GROUP BY r.etat LIMIT 7", nativeQuery = true)
	public List<Object[]> ReclamtionPerDayEtat(Integer date);
	//
	@Query(value = "SELECT COALESCE((SELECT COUNT(r.id) as NB FROM reclamation r WHERE CAST(r.date_rec AS DATE)=DATE(NOW( ))-?1 AND r.etat=?2 GROUP BY r.etat LIMIT 7 ),0)", nativeQuery = true)
	public int ReclamtionPerDayEtat(Integer date, String etat);

}
