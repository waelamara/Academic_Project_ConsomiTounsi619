package tn.esprit.spring.Repository.Charite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Charite.Endroit;

public interface EndroitRepository extends JpaRepository<Endroit, Long> {
	@Modifying
	@Query(value = "UPDATE `t_endroit` SET `statu`=?1 WHERE `id`=?2", nativeQuery = true)
	public void ReserveEndroit(String statu1, Long id);
	
	@Query(value = "SELECT * FROM t_endroit WHERE statu='disponible'", nativeQuery = true)
	public List<Endroit> ListeEndroit();
	@Query(value = "SELECT * FROM `t_endroit` WHERE `eventss`=?1", nativeQuery = true)
	public List<Endroit> ListeEndroitE(Long idevntss);

}
