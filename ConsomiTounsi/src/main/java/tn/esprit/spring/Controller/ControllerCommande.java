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
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.GestionProduit.Produit;

@RestController
@RequestMapping("/commande")
public class ControllerCommande {
	@Autowired
	CommandeDAO commandeDao;
	@PostMapping("/ajouter")
	public Commande AjouterCommande(@Valid @RequestBody Commande c)
	{
		return commandeDao.save(c);
	}
	@GetMapping("/afficher")
	public List<Commande> getAllCommande() {
		return commandeDao.findAll();
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Commande> DeleteCommande(@PathVariable(value = "id") Long idCommande) {
		Commande c =commandeDao.findOne(idCommande);
		if ( c == null) {
			return ResponseEntity.notFound().build();
		}
		commandeDao.Delete(c);
		return ResponseEntity.ok().build();
	}
	@GetMapping("rechercheparcode/{code}")
	public List<Commande>Commandeparcode(@PathVariable(value = "code") int code) {
	
		return commandeDao.Commandeparcode(code);
	}
	 

	
	@GetMapping("/recherchetype/{type}")
	public List<Commande> CommandeparType(@PathVariable(value = "type") String type) {
		return commandeDao.CommandeparType(type);
	}
	@GetMapping("/recherche/{idClient}")
	public List<Commande> CommandeparClient(@PathVariable(value = "idClient") int id) {
		return commandeDao.CommandeparClient(id);
	}


	

}
