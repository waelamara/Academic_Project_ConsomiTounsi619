package tn.esprit.spring.Repository.Produit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit.SsCategorie;

public interface SousSousCategorieRepository extends JpaRepository<SsCategorie, Long> {
	@Query(value = "SELECT * FROM ss_categorie WHERE idscategorie_id=?1", nativeQuery = true)
	public List<SsCategorie> findByIdSCategorie(Long id);
	@Query(value = "SELECT * FROM ss_categorie WHERE nom_ss_categorie=?1", nativeQuery = true)
	public SsCategorie findSsCategorieByName(String name);
}
