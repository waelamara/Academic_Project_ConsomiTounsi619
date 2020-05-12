
package tn.esprit.spring.Service.Stock;



import java.util.List;

import tn.esprit.spring.Model.Rayon.Rayon;
import tn.esprit.spring.Model.Stock.Stock;

public interface IStockService {
	
	Stock saveStock(Stock stock);

	Stock updateStock(Stock stock);
	
	void deleteStockById(Long idstock);
	
	List<Stock> allStock();
	public List<Stock> findStockbyName(String name);
	
    public void affecterStockAProduit(Long idstock,Long Idproduit);
    
    public void PrixVenteParStock(Long idproduit);
    
    public void effectuerCommande(Long idcommande);
}

