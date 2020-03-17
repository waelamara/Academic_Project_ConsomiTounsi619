package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Model.Events;
@Repository
public interface EventsRepository extends JpaRepository<Events, Long> {
	/*@Query(value = "SELECT * FROM t_events WHERE titre LIKE ?1%", nativeQuery = true)
	public List<Events> findLikeName(String string);*/

}