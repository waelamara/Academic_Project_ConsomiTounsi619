package tn.esprit.spring.Service.Panier;



import java.beans.Transient;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Service;

import tn.esprit.spring.Model.CadeauUser;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.User;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.Produit.Produit;
import tn.esprit.spring.Model.Stock.Stock;
import tn.esprit.spring.Repository.CadeauUserRepository;
import tn.esprit.spring.Repository.CommandeRepository;
import tn.esprit.spring.Repository.LigneCommandeRepository;
import tn.esprit.spring.Repository.UserRepository;
import tn.esprit.spring.Repository.Stock.StockRepository;
@Service
public class CommandeDAO implements ICommande {
	@Autowired
	CommandeRepository commandeRepository;
	@Autowired
	LigneCommandeRepository ligneCommandeRepository;
	@Autowired
	StockRepository stockRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	CadeauUserRepository cadeauUserRepository; 

	public Commande save (Commande c)
	{
	    
	ZoneId zid = ZoneId.of("Africa/Tunis");
		c.setDate(LocalDate.now(zid));
		
		return commandeRepository.save(c);
	}
	
    
	public List<Commande> CommandeparType(String type) {
		return commandeRepository.CommandeparType(type);

	}
	public List<Commande> CommandeparClient(int id) {
		return commandeRepository.CommandeparClient(id);

	}
	public List<Commande> findAll() {
		return commandeRepository.findAll();
	}
	public void Delete(Commande c) {
		commandeRepository.delete(c);
	}

	public Commande findOne(Long id) {
		return commandeRepository.getOne(id);
	}
	public List< Commande> Commandeparcode( long code) {
		return commandeRepository.Commandeparcode(code);

	}

	
	
	public void PayerEnLigne(int idCommande,int iduser,String code)

	{
	
		/*
		//List<lignecommandeproduit>List=	ligneCommandeRepository.panierParIdclient(iduser);
		List<LigneCommande> Linges=ligneCommandeRepository.findAll();
	int a=0;
	//Float montant= cadeauUserRepository.montantCadeau(code);
	CadeauUser cd=cadeauUserRepository.verifierCode(code);

		 for (LigneCommande l : Linges)
		 {
			 if(l.getCommande().getId()==idCommande)
			 {
			 a= l.getProduit().getIdStock().getQuantite()-l.getQuantity();
			 long idStock=l.getProduit().getIdStock().getIdstock();
				System.out.println(idStock);
				Stock c =stockRepository.getOne(idStock);
				c.setQuantite(a);
				 stockRepository.save(c);
			 }
			
			
			 
		 }
		commandeRepository.PayerEnLigne(idCommande);
		commandeRepository.remise(iduser);
		
		*/
	}
	
	
	
	public void PayerPorteaPorte(int idCommande,int iduser)
	{
		
		/*
		
		List<LigneCommande> Linges=ligneCommandeRepository.findAll();
	int a=0;
		 for (LigneCommande l : Linges)
		 {
			 if(l.getCommande().getId()==idCommande)
			 {
			 a= l.getProduit().getIdStock().getQuantite()-l.getQuantity();
			 long idStock=l.getProduit().getIdStock().getIdstock();
				System.out.println(idStock);
				Stock c =stockRepository.getOne(idStock);
				c.setQuantite(a);
				 stockRepository.save(c);
			 }
			Commande c= commandeRepository.findById((long) idCommande).get();
			c.getMontant();
			User u =userRepository.findById((long) iduser).get();
			 u.setPointFidelite(Math.round((int) c.getMontant()/ 10));
			 userRepository.save(u);
		commandeRepository.PayerPorteaPorte(idCommande);
		commandeRepository.remise(iduser);
	}
	
*/
}


	public List<Object[]> NumCommadeParMOIS()
	{
		
		return commandeRepository.NumCommadeParMOIS();
	}
}