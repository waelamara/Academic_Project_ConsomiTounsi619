package tn.esprit.spring.Repository.Charite;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.Model.Charite.Events;
import tn.esprit.spring.Model.Stock.Stock;
@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
	@Query(value = "SELECT * FROM t_events WHERE titre LIKE ?1%", nativeQuery = true)
	public List<Events> findLikeName(String string);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM t_events WHERE dateE < LOCALTIMESTAMP")
	public int  removeOlder();
	
}