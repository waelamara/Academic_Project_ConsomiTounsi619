package tn.esprit.spring.Service.GestionUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.reclamationRepository;
import tn.esprit.spring.Repository.Charite.ChariteRepository;
import tn.esprit.spring.Repository.Forum.SujetRepository;
import tn.esprit.spring.Repository.Produit.ProduitRepository;

@Service
public class ChartService {
	@Autowired
	UserRepository  userRepository;
	@Autowired 
	CommandeRepository commandeRepository;
	@Autowired
	ChariteRepository chariteRepository;
	@Autowired
	SujetRepository sujetRep;
	@Autowired
	reclamationRepository recRep;
	@Autowired
	ProduitRepository produitRep;
	
	public Long getNombreOrders()
	{
		return commandeRepository.NombreCommade();
	}
	public float getTotalSales()
	{
		return commandeRepository.TotalSales();
	}
	public float getTotalDonation()
	{
		return chariteRepository.TotalDonation();
	}
	
	
	public List<Object[]> Top5Produit()
	{
		return commandeRepository.Top5Product();
	}
	
	public List<Object[]> SujetByCategory()
	{
		return sujetRep.SujetByCategoty();
	}
	
	public List<Object[]> ReclamtionPerDayEtat(Integer date)
	{
		return recRep.ReclamtionPerDayEtat(date);
	}
	
	public List<Produit> getLast5Products()
	{
		return produitRep.getLast5Products();
	}

}
