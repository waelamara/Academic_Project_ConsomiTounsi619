
package tn.esprit.spring.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Livraison;

public interface LivraisonRepository extends JpaRepository <Livraison, Long> {
	
	@Query(value = "SELECT `produit_id` FROM `ligne_commande` WHERE `commande_id` =?1", nativeQuery = true)
	public List<String> ListProduit_idcommande(float id_commande);
	
	@Query(value = "SELECT * FROM `livraison` WHERE `delivery_user_id`=?1", nativeQuery = true)
	public List<Livraison> Listedemission(long id_livreur);
	
	
	
	
	
	//SELECT COUNT(*) FROM livraison WHERE `livreur_id` =1


}
