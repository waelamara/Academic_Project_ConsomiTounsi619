
package tn.esprit.spring.Service.Stock;



import java.util.List;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Stock.Stock;

public interface IStockService {
	
	Stock saveStock(Stock stock);

	
	
	void deleteStockById(Long idstock);
	public void ajouterStockbyProd(Stock stock,Long idprod);
	
	List<Stock> allStock();
	public List<Stock> findStockbyName(String name);
	
    public void affecterStockAProduit(Long idstock,Long Idproduit);
    
    public void PrixVenteParStock(Long idproduit);
    
    public void effectuerCommande(Long idcommande);
    public List<Produit> getProduits() ;
    
    public void displayStock();
    public Long updateStockjsf(Stock stock);

    public Stock getStockbyId(Long idstock);

	Stock updateStock(Stock stock);
}

