<<<<<<< HEAD
package tn.esprit.spring.Service.Stock;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.criterion.BetweenExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Repository.ProduitRepository;
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

	}

















}
=======
package tn.esprit.spring.Service.Stock;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
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
/*
	@Override
	public void affecterProduitAStock(Long Idproduit, Long idstock) {
		Produit produit = produitRepository.findById(Idproduit).get();
		Stock stock=stockRepository.findById(idstock).get();
		 produit.setIdStock(stock);
		produitRepository.save(produit);
	}
*/
	
	
	@Override
	public List<Stock> allStock() {
		
		return stockRepository.findAll();
	}
	
	
}
>>>>>>> branch 'master' of https://github.com/waelamara/ConsomiTounsi619
