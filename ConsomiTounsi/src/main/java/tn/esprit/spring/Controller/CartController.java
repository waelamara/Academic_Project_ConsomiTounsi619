package tn.esprit.spring.Controller;

import java.util.List;

import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	
	
	public List<lignecommandeproduit> panierParIdclient(long id)
	{
		
		return ligneCommandeDao.panierParIdclient(id);
	}
	public void deleteLigne( Long idLigneCommande) {
		LigneCommande lc = ligneCommandeDao.findOne(idLigneCommande);
		ligneCommandeDao.DeleteLigne(idLigneCommande);
	}
	public List<Commande> commandeparClient(int id) {
		return commandeDao.CommandeparClient(id);

	}
	
	

}
