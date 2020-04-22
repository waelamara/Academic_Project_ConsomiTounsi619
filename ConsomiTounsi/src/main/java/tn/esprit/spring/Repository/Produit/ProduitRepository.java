package tn.esprit.spring.Repository.Produit;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.Produit.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
	@Query(value = "SELECT * FROM produit WHERE nom_produit LIKE ?1%", nativeQuery = true)
	public List<Produit> findLikeName(String string);

	@Query(value = "SELECT * FROM produit  WHERE id_ss_categorie_id=?1", nativeQuery = true)
	public List<Produit> findProduitSsCategorie(Long idSsCategorie);

	@Query(value = "SELECT * FROM produit p JOIN ss_categorie ssc ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id WHERE sc.id=?1", nativeQuery = true)
	public List<Produit> findProduitSCategorie(Long idSCategorie);

	@Query(value = "SELECT * FROM produit p JOIN ss_categorie ssc ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id JOIN categorie c ON sc.id_categorie_id=c.id WHERE c.id=?1", nativeQuery = true)
	public List<Produit> findProduitCategorie(Long idCategorie);
	
	@Query(value = "SELECT * FROM produit WHERE Idrayon =?1", nativeQuery = true)
	public List<Produit> findProduitParRayon(Long Idrayon);
	
	@Query(value = "SELECT p.id FROM produit p JOIN ss_categorie ssc "
			+ "ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id "
			+ "JOIN categorie c ON sc.id_categorie_id=c.id WHERE c.nom_categorie like ?1 and p.prix<600", nativeQuery = true)
	public List<String>  findProduitCategorbyName(String nom );
	
	
}
