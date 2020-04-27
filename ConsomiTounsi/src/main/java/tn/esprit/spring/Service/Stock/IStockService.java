<<<<<<< HEAD
package tn.esprit.spring.Service.Stock;



import java.util.List;

import tn.esprit.spring.Model.Stock.Stock;

public interface IStockService {
	
	Stock saveStock(Stock stock);

	Stock updateStock(Stock stock);
	
	void deleteStockById(Long idstock);
	
	List<Stock> allStock();
	
    public void affecterStockAProduit(Long idstock,Long Idproduit);
    
    public void PrixVenteParStock(Long idproduit);
    
    public void effectuerCommande(Long idcommande);
}
=======
package tn.esprit.spring.Service.Stock;



import java.util.List;

import tn.esprit.spring.Model.Stock.Stock;

public interface IStockService {
	
	Stock saveStock(Stock stock);

	Stock updateStock(Stock stock);
	
	void deleteStockById(Long idstock);
	
	List<Stock> allStock();
	
    public void affecterStockAProduit(Long idstock,Long Idproduit);
    
    public void PrixVenteParStock(Long idproduit);
    
    public void effectuerCommande(Long idlignecommande);
}
>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619
