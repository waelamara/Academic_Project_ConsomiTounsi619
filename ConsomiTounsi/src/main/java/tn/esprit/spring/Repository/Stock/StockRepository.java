
package tn.esprit.spring.Repository.Stock;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Stock.Stock;

public interface StockRepository extends JpaRepository <Stock, Long>  {
	
	@Query(value = "SELECT * FROM Stock WHERE nom_stock LIKE ?1%", nativeQuery = true)
	public List<Stock> findStockbyName(String name);

	
	@Query(value = "SELECT * FROM Stock WHERE id_produit_id LIKE ?1% AND MONTH(NOW())-MONTH(validite)<2 and YEAR(validite)=YEAR(NOW()) ORDER BY validite", nativeQuery = true)
	public List<Stock> findStockbyProduit(Long idstock);
	
	
	

	@Query(value = "SELECT * FROM LigneCommande WHERE status='payee' AND produit_id LIKE ?1%", nativeQuery = true)
	public List<LigneCommande> findLigneCommandePayee(Long idlignecommande);
	

	@Query(value = "SELECT * FROM Stock WHERE produit_id LIKE ?1%", nativeQuery = true)
	public List<Stock> stockparproduit(Long idproduit);
	
	//raed
	@Query(value = "SELECT SUM(s.quantite) FROM stock as s WHERE s.validite>Date(now()) AND s.id_produit_id=?1", nativeQuery = true)
	public int getProduitStockNew(Long idproduit);
	@Query(value = "SELECT SUM(s.quantite) FROM stock as s WHERE s.validite<Date(now()) AND s.id_produit_id=?1", nativeQuery = true)
	public int getProduitStockOld(Long idproduit);
	@Query(value = "SELECT DISTINCT s.id_produit_id FROM stock as s ", nativeQuery = true)
	public List<Long> getListProduitStock();
	
	///


}
