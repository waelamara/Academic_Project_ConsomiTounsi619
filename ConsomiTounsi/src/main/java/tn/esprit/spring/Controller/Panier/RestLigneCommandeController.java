package tn.esprit.spring.Controller.Panier;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import tn.esprit.spring.Model.LigneCommande;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.Chart.SexeC;
import tn.esprit.spring.Service.Panier.LigneCommandeImpl;


@RestController
@RequestMapping("/Panier")
public class RestLigneCommandeController {
	
	@Autowired
	LigneCommandeImpl ligneCommandeDao;
	
	
	//http://localhost:8081/Panier/panier/1
	@GetMapping("panier/{idUser}")
	public List<lignecommandeproduit> panierParIdclient(@PathVariable(value = "idUser") long id) {
	
		return ligneCommandeDao.panierParIdclient(id);
	}
	
	
	@GetMapping("/{idprod}/{idUser}/{idCommande}")
	public LigneCommande findLigneCommande(@PathVariable(value = "idprod") long idprod,@PathVariable(value = "idUser") long iduser,@PathVariable(value = "idCommande") long idCommande) {
	
		return ligneCommandeDao.findLigneCommande(idprod,iduser,idCommande);
	}
	
	
	//http://localhost:8081/Panier/ajouter/3/1
	// { "quantity":"5"} 
	@PostMapping("/ajouter/{idprod}/{iduser}")
	public List<lignecommandeproduit> AjouterLigne (@PathVariable(value = "idprod") Long idprod,@PathVariable(value = "iduser") Long iduser,
			@Valid @RequestBody LigneCommande lc) {
	
			ligneCommandeDao.AjouterAuPanier(idprod, iduser, lc);
		
		return ligneCommandeDao.panierParIdclient(iduser);
	}
	
	
	//http://localhost:8081/Panier/Categories
	@GetMapping("/Categories")
	public List<Object[]> NumCategorie()
	{
		
		return ligneCommandeDao.NumCategorie();
	}
//	@GetMapping("/Categories2")
//	public List<SexeC> NumCategorie2()
//	{
//		
//		return ligneCommandeDao.NumCategorie2();
//	}
	
	
	@GetMapping("Produit/{idprod}")
	public int NumProduitVendu(@PathVariable(value = "idprod") Long idProduit)
	{
		return ligneCommandeDao.NumProduitVendu (idProduit);
	}
	@DeleteMapping("DeleteLigne/{idLigne}")
	public void DeleteLigne(@PathVariable(value = "idLigne") Long idLigneCommande)
	{
		ligneCommandeDao.deleteLigne(idLigneCommande);
	}
	@PutMapping("update/{idLigne}/{qty}")
	 public void updateLigne(@PathVariable(value = "idLigne")long idL,@PathVariable(value = "qty")int quantity)
	 {
		 ligneCommandeDao.updateLigne(idL, quantity); 
	 }
	@GetMapping("numProduit/{iduser}")
	 public int numProduitPanier(@PathVariable(value = "iduser")Long iduser)
	 {
		 int a=ligneCommandeDao.numProduitPanier(iduser);
		 System.out.println(ligneCommandeDao.numProduitPanier(iduser));
		 return a;
	 }
	@GetMapping("PanierParidCommande/{idCommande}")
	 public List<lignecommandeproduit> panierParIdCommande(@PathVariable(value = "idCommande")long idCommande)
	 {
		 return ligneCommandeDao.panierParIdCommande(idCommande);
	 }
	 
}
