package tn.esprit.spring.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long>{
	@Query(value = "SELECT * FROM produit WHERE nom_produit LIKE ?1%", nativeQuery = true)
	public List<Produit> findLikeName(String string);
//	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
//	Date dateDebut = dateFormat.parse("30/09/2019");
	
	
}
