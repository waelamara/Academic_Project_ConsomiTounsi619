package tn.esprit.spring.Repository.Charite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Charite.Charite;


public interface ChariteRepository extends JpaRepository<Charite, Long>{
	@Query(value="select * from t_charite where iduser_user_id=?1 ",nativeQuery=true)
	public List<Charite> getCharite(Long id);
	
	@Query(value="select * from t_charite where iduser_user_id=?1 ",nativeQuery=true)
	public Charite getChariteUser(Long id);
	@Query(value="SELECT commande_charite_id FROM t_charite_commande_charite WHERE `charite_id`=?1",nativeQuery=true)
	public List<Long> getChariteCommande(Long id);
	
}
