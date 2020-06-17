package tn.esprit.spring.Repository.Charite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Charite.Endroit;
import tn.esprit.spring.Model.Charite.Pub;

public interface PubRepository extends JpaRepository<Pub, Long>{

	@Query(value = "SELECT * FROM t_pub WHERE nom LIKE ?1%", nativeQuery = true)
	public List<Pub> findLikeName(String nom);
	

}
