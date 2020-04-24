package tn.esprit.spring.Repository.Produit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit.SCategorie;

public interface SousCategorieRepository extends JpaRepository<SCategorie, Long> {

	@Query(value = "SELECT * FROM scategorie WHERE id_categorie_id=?1", nativeQuery = true)
	public List<SCategorie> findByIdCategorie(Long id);
}
