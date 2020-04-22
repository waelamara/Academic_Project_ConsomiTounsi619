package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Livreur;

public interface LivreurRepository extends JpaRepository <Livreur, Long>  {
	@Query(value = "SELECT COUNT(*) FROM livraison c WHERE livreur_id =?1 AND MONTH(NOW())-MONTH(c.date_affec_livr)=1 and YEAR(c.date_affec_livr)-YEAR(NOW())=0 OR MONTH(NOW())-MONTH(c.date_affec_livr)=0", nativeQuery = true)
	public  int NbMission_livreur(int id_liv);
	
	

}
