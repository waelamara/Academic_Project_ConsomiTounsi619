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
	
	@Query(value = "SELECT p.id FROM produit p JOIN ss_categorie ssc ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id JOIN categorie c ON sc.id_categorie_id=c.id "
			+ "WHERE( p.id NOT IN (select b.produit_id from ligne_commande as b )"
			+ " OR p.id In (SELECT produit_id FROM ligne_commande GROUP by produit_id HAVING count(*)<=3)) "
			+ "and c.nom_categorie =?1 and p.prix<600", nativeQuery = true)
	public List<String>  findProduitCategorbyName(String nom);
	
	@Query(value = "SELECT * FROM produit WHERE id=?1", nativeQuery = true)
	public Produit findProduit(Long idProduit);
	@Query(value = "SELECT * FROM produit WHERE barcode LIKE ?1%", nativeQuery = true)
	public List<Produit> findProduitByBarcode(String barcode);
	
	@Query(value = "SELECT * FROM `produit` ORDER by id DESC LIMIT 3", nativeQuery = true)
	public List<Produit> getLast5Products();
	
	
	@Query(value = "SELECT * FROM produit p JOIN ligne_commande l on p.id=l.produit_id JOIN commande c on l.commande_id =c.id WHERE c.status='payee' GROUP BY p.id ORDER BY SUM(l.quantity) DESC LIMIT 16", nativeQuery = true)
	public List<Produit> MostPopularProducts();
	@Query(value = "SELECT Sum(l.quantity) as n FROM produit p JOIN ligne_commande l on p.id=l.produit_id JOIN commande c on l.commande_id =c.id WHERE p.id=2 and c.status='payee'", nativeQuery = true)
	public int QuantiteProduitdeMoisVendu(Long idProduit);
	@Query(value = "SELECT * FROM produit p JOIN ss_categorie ssc ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id JOIN categorie c ON sc.id_categorie_id=c.id WHERE c.id=?1 LIMIT 12", nativeQuery = true)
	public List<Produit> MostPopularCategorieProducts(Long idCategorie);
	
	@Query(value = "SELECT * FROM produit p JOIN ss_categorie ssc ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id JOIN categorie c ON sc.id_categorie_id=c.id WHERE c.id=?1 and p.nom_produit LIKE ?2%", nativeQuery = true)
	public List<Produit> findProduitCategorieAndName(Long idCategorie,String Name);
	@Query(value = "SELECT * FROM produit  WHERE id_ss_categorie_id=?1 and p.nom_produit LIKE ?2%", nativeQuery = true)
	public List<Produit> findProduitSsCategorieAndName(Long idSsCategorie,String Name);
	@Query(value = "SELECT * FROM produit p JOIN ss_categorie ssc ON p.id_ss_categorie_id=ssc.id JOIN scategorie sc ON ssc.idscategorie_id=sc.id WHERE sc.id=?1 and p.nom_produit LIKE ?2%", nativeQuery = true)
	public List<Produit> findProduitSCategorieAndName(Long idSCategorie,String Name);
}
