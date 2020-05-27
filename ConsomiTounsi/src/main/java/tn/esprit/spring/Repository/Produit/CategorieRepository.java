package tn.esprit.spring.Repository.Produit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit.Categorie;


public interface CategorieRepository extends JpaRepository<Categorie, Long> {
	
	@Query(value = "SELECT * FROM categorie WHERE nom_categorie=?1", nativeQuery = true)
	public Categorie findCategorieByName(String name);
}


