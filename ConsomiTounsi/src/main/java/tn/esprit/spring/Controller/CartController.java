package tn.esprit.spring.Controller;

import java.util.List;

import javax.transaction.Transactional;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Service.Panier.CommandeImpl;
import tn.esprit.spring.Service.Panier.LigneCommandeImpl;


@Controller(value = "cartController")
@ELBeanName(value = "cartController")
@Join(path = "/Cart", to = "/Cart.jsf")
public class CartController {
	
	@Autowired
	LigneCommandeImpl ligneCommandeDao;
	@Autowired
	CommandeImpl commandeDao;
	private long id;
	
	private int qty;
	
	
	
	public LigneCommandeImpl getLigneCommandeDao() {
		return ligneCommandeDao;
	}


	public void setLigneCommandeDao(LigneCommandeImpl ligneCommandeDao) {
		this.ligneCommandeDao = ligneCommandeDao;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}
	
	
	public List<lignecommandeproduit> panierParIdclient(long id)
	{
		
		return ligneCommandeDao.panierParIdclient(id);
	}
	
public void deleteLigne(long idLigneCommande) {
		
	ligneCommandeDao.deleteLigne(idLigneCommande);
		
}
	public List<Commande> commandeparClient(int id) {
		return commandeDao.CommandeparClient(id);

	}
	
	 public void updateLigne()
	 {
		 
		 ligneCommandeDao.updateLigne2(new LigneCommande(qty)); 
	 }
	 public void display(LigneCommande lc)
		{
			
		 this.setQty(lc.getQuantity());
			

		}
	 
	
	

}
