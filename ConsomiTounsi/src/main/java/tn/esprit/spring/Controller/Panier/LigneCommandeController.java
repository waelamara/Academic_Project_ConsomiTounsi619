package tn.esprit.spring.Controller.Panier;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Service.Panier.LigneCommandeDao;
@Controller
public class LigneCommandeController {
	@Autowired
	LigneCommandeDao ligneCommandeDao;
	public List<lignecommandeproduit> panierParIdclient(long id) {
		
		return ligneCommandeDao.panierParIdclient(id);
	}
	public LigneCommande findLigneCommande( long idprod,long iduser,long idCommande) {
		
		return ligneCommandeDao.findLigneCommande(idprod,iduser,idCommande);
	}
	public List<lignecommandeproduit> AjouterLigne (Long idprod,Long iduser, LigneCommande lc) 
		{
	
			ligneCommandeDao.AjouterAuPanier(idprod, iduser, lc);
		
		return ligneCommandeDao.panierParIdclient(iduser);
	}
	public List<Object[]> NumCategorie()
	{
		return ligneCommandeDao.NumCategorie();
	}
}
