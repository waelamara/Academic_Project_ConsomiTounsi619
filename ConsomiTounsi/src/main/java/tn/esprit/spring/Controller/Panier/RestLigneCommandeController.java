package tn.esprit.spring.Controller.Panier;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Service.Panier.LigneCommandeDao;


@RestController
@RequestMapping("/Panier")
public class RestLigneCommandeController {
	@Autowired
	LigneCommandeDao ligneCommandeDao;
	@GetMapping("/{idUser}")
	public List<lignecommandeproduit> panierParIdclient(@PathVariable(value = "idUser") long id) {
	
		return ligneCommandeDao.panierParIdclient(id);
	}
	@GetMapping("/{idprod}/{idUser}/{idCommande}")
	public LigneCommande findLigneCommande(@PathVariable(value = "idprod") long idprod,@PathVariable(value = "idUser") long iduser,@PathVariable(value = "idCommande") long idCommande) {
	
		return ligneCommandeDao.findLigneCommande(idprod,iduser,idCommande);
	}
	@PostMapping("/ajouter/{idprod}/{iduser}")
	public List<lignecommandeproduit> AjouterLigne (@PathVariable(value = "idprod") Long idprod,@PathVariable(value = "iduser") Long iduser,
			@Valid @RequestBody LigneCommande lc) {
	
			ligneCommandeDao.AjouterAuPanier(idprod, iduser, lc);
		
		return ligneCommandeDao.panierParIdclient(iduser);
	}
	@GetMapping("/Categories")
	public List<Object[]> NumCategorie()
	{
		return ligneCommandeDao.NumCategorie();
	}


}
