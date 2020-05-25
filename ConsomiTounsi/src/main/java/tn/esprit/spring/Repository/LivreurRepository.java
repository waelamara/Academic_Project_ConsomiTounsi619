package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Livreur;

public interface LivreurRepository extends JpaRepository <Livreur, Long>  {
	@Query(value = "SELECT COUNT(*) FROM livraison c WHERE livreur_id =?1 AND MONTH(NOW())-MONTH(c.date_affec_livr)=1 and YEAR(c.date_affec_livr)-YEAR(NOW())=0 OR MONTH(NOW())-MONTH(c.date_affec_livr)=0", nativeQuery = true)
	public  int NbMission_livreur(int id_liv);
	@Query(value = "SELECT * FROM `livreur` WHERE `etat`=?1 AND `disponible`=?2  ORDER BY RAND() LIMIT 1", nativeQuery = true)
	public Livreur findparhasard(String etat,String dispo);
	@Modifying
	@Query(value = "UPDATE `livreur` SET `etat`=?1 WHERE `id`=?2", nativeQuery = true)
	public void ConfirmerLiv(String etat1,long id);
	
	@Query(value ="SELECT * FROM `livreur` WHERE `disponible`=?1",nativeQuery = true)
	public List <Livreur> LivreursDispo(String dispo);
	
	

}
