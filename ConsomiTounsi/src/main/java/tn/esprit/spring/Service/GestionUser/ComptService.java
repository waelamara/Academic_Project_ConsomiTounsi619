package tn.esprit.spring.Service.GestionUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Produit.ProduitRepository;
import tn.esprit.spring.Repository.Publicite.PubliciteRepository;
import tn.esprit.spring.Repository.Stock.StockRepository;

@Service
public class ComptService {
	@Autowired
	ProduitRepository produitRep;
	@Autowired
	PubliciteRepository pubRep;
	@Autowired
	StockRepository stockRep;
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	UserRepository userRepository;
	
	public float TotalProfitPub()
	{
		return pubRep.TotalProfitPub();
	}
	
	public float TotalCouts()
	{
		return commandeRepository.CoutSales();
	}
	
	public float getTotalSalaries()
	{
		return userRepository.TotalSalaries();
	}

}
