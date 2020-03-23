package tn.esprit.spring.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.CommandeDAO;
import tn.esprit.spring.DAO.FactureDAO;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Facture;
import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Model.lignecommandeproduit;
import tn.esprit.spring.Model.GestionProduit.Produit;


@RestController
@RequestMapping("/facture")
public class FactureController {
	@Autowired
	FactureDAO factureDAO;
	@Autowired
	CommandeDAO commandeDao;
	@PostMapping("/ajouter/{idCommande}")
	public ResponseEntity <Facture> AjouterFacture(@PathVariable(value = "idCommande") Long idCommande,@Valid @RequestBody Facture f) 
			
	{
		Commande c =  commandeDao.findOne( idCommande);
		f.setCommande(c);
		
			factureDAO.save(f);
			return ResponseEntity.ok().build();
	
	}
	@GetMapping("/afficher")
	public List<Facture > getAllFacture(){
		
		return factureDAO.findAll();
		
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Facture> DeleteProduit(@PathVariable(value = "id") Long idFacture) {
		Facture f = factureDAO.findOne(idFacture);
		if (f == null) {
			return ResponseEntity.notFound().build();
		}
		factureDAO.Delete(f);
		return ResponseEntity.ok().build();
	}
	@GetMapping("/{idUser}")
	public List<lignecommandeproduit> panierParIdclient(@PathVariable(value = "idUser") long id) {
	
		return factureDAO.FactureParIdUser(id);
	}

}
