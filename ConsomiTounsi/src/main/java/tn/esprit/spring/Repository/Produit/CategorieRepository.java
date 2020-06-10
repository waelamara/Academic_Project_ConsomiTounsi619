package tn.esprit.spring.Repository.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit.Categorie;


public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
	@Query(value = "SELECT * FROM categorie WHERE nom_categorie=?1", nativeQuery = true)
	public Categorie findCategorieByName(String name);
	
	@Query(value = "SELECT * FROM categorie c JOIN scategorie sc on c.id=sc.id_categorie_id join ss_categorie ssc on sc.id=ssc.idscategorie_id join produit p on ssc.id=p.id_ss_categorie_id join ligne_commande l ON p.id=l.produit_id GROUP BY c.id ORDER BY SUM(l.quantity)  DESC LIMIT 1", nativeQuery = true)
	public Categorie MostPopularCategorie();
}


