
<<<<<<< HEAD
package tn.esprit.spring.Service.Stock;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
=======
package tn.esprit.spring.Service.Stock;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Repository.LigneCommandeRepository;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
<<<<<<< HEAD
import tn.esprit.spring.Repository.Stock.StockRepository;

@Service
public class StockServiceImpl implements IStockService {
	@Autowired
	StockRepository stockRepository;
	@Autowired
	ProduitRepository produitRepository;
	@Autowired
	LigneCommandeRepository lignecommande;

	@Override
	public Stock saveStock(Stock stock) {

		return stockRepository.save(stock);
	}

	@Override
	public Stock updateStock(Stock stock) {

		return stockRepository.saveAndFlush(stock);
	}

	@Override
	public void deleteStockById(Long idstock) {
		stockRepository.deleteById(idstock);

	}

	@Override
	public void affecterStockAProduit(Long idstock, Long Idproduit) {
		Produit produit = produitRepository.findById(Idproduit).get();
		Stock stock = stockRepository.findById(idstock).get();
		stock.setIdProduit(produit);
		stockRepository.save(stock);
	}

	@Override
	public List<Stock> allStock() {

		return stockRepository.findAll();
	}

	@Override
	public void PrixVenteParStock(Long idproduit) {

		List<Stock> stock = stockRepository.findStockbyProduit(idproduit);
		for (Stock s : stock) {
			if (s.getQuantite() > 70 ) {
				s.setPrixremise( (float) (s.getPrixdevente() * 0.5));
stockRepository.save(s);
			}

		}

=======
import tn.esprit.spring.Repository.Stock.StockRepository;

@Service
public class StockServiceImpl implements IStockService {
	@Autowired
	StockRepository stockRepository;
	@Autowired
	ProduitRepository produitRepository;

	@Override
	public Stock saveStock(Stock stock) {

		return stockRepository.save(stock);
	}

	@Override
	public Stock updateStock(Stock stock) {

		return stockRepository.saveAndFlush(stock);
	}

	@Override
	public void deleteStockById(Long idstock) {
		stockRepository.deleteById(idstock);

	}

	@Override
	public void affecterStockAProduit(Long idstock, Long Idproduit) {
		Produit produit = produitRepository.findById(Idproduit).get();
		Stock stock = stockRepository.findById(idstock).get();
		stock.setIdProduit(produit);
		stockRepository.save(stock);
	}

	@Override
	public List<Stock> allStock() {

		return stockRepository.findAll();
	}

	@Override
	public void PrixVenteParStock(Long idproduit) {

		List<Stock> stock = stockRepository.findStockbyProduit(idproduit);
		for (Stock s : stock) {
			if (s.getQuantite() > 70 ) {
				s.setPrixremise( (float) (s.getPrixdevente() * 0.5));
stockRepository.save(s);
			}

		}

>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619
	}

	
	@Override
	public void effectuerCommande(Long idcommande) {
		 
		    
		
<<<<<<< HEAD
		LocalDate d = LocalDate.now();
		Date day=java.sql.Date.valueOf(d);
		List<Stock> stock=new ArrayList<>() ;
		List<LigneCommande> l=lignecommande.findAll();	
		for (LigneCommande lc: l)
		{
			if ( lc.getCommande().getId()==idcommande) 
			{
				lc.getQuantity();
				 stock = stockRepository.findStockbyProduit(lc.getProduit().getId());
			int	a= stock.get(0).getQuantite()-lc.getQuantity();
			if( a<=0 ) {
				
				deleteStockById(stock.get(0).getIdstock());
				 stock = stockRepository.findStockbyProduit(lc.getProduit().getId());
				 int	b= stock.get(0).getQuantite()-lc.getQuantity();
				 Stock s=stock.get(0);
				 stock.get(0).setQuantite(b);
				 stockRepository.save(s);
			}
			
			Stock s=stock.get(0);
			 stock.get(0).setQuantite(a);
			 stockRepository.save(s);
				
					}		
			
		}
		
	
	
	}












=======
	}












>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619
}