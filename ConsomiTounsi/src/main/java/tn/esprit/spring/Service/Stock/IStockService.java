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
