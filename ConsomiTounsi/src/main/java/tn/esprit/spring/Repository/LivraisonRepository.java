
package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Livraison;

public interface LivraisonRepository extends JpaRepository <Livraison, Long> {
	
	@Query(value = "SELECT `produit_id` FROM `ligne_commande` WHERE `commande_id` =?1", nativeQuery = true)
	public List<String> ListProduit_idcommande(float id_commande);
	
	@Query(value = "SELECT * FROM `livraison` WHERE `delivery_user_id`=?1", nativeQuery = true)
	public List<Livraison> Listedemission(long id_livreur);
	
	@Modifying
	@Query(value = "UPDATE `livraison` SET `etat`=?1 WHERE `id`=?2", nativeQuery = true)
	public void DoneMission(Integer etat,long Livraison);
	//UPDATE `livraison` SET `etat`=1 WHERE `id`=1
	//	
	@Query(value = "SELECT * FROM `livraison` c WHERE `delivery_user_id`=?1 AND YEAR(c.date_affec_livr)-YEAR(NOW())=0 AND MONTH(NOW())-MONTH(c.date_affec_livr)=0  AND DAY(NOW())-DAY(c.date_affec_livr)=0 AND`etat`=0", nativeQuery = true)
	public List<Livraison> ListedemissionToday(long id_livreur);
	//liste mission par mois
	@Query(value = "SELECT * FROM `livraison` c WHERE `delivery_user_id`=?1 AND YEAR(c.date_affec_livr)-YEAR(NOW())=0 AND MONTH(NOW())-MONTH(c.date_affec_livr)=0 ", nativeQuery = true)
	public List<Livraison> ListedemissionMonth(long id_livreur);
	
	
	
	
	


}
