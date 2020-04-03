package tn.esprit.spring.Controller.Livraison;

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

import tn.esprit.spring.DAO.CommandeDAO;
import tn.esprit.spring.Model.Commande;
import tn.esprit.spring.Model.Livraison;
import tn.esprit.spring.Model.Livreur;
import tn.esprit.spring.Service.Livraison.LivraisonService;
import tn.esprit.spring.Service.Livreur.LivreurService;

@RestController
@RequestMapping("/livraison")
public class LivraisonContoller {

	@Autowired
	LivraisonService livraisonService;
	
	@Autowired
	CommandeDAO CommandeDAO;
	
	@Autowired
	LivreurService LivreurService;

	/* Enregistrer un livreur */
	@PostMapping("/ajout")

	public Livraison createLivreur(@Valid @RequestBody Livraison liv) {
		if((liv.getCommande_id()!=0)&&(liv.getLivreur_id()!=0)){
			Commande c = CommandeDAO.findOne(liv.getCommande_id());
			liv.setCommande(c);
			Livreur L = LivreurService.findOne(liv.getLivreur_id());
			liv.setLivreur(L);
		return livraisonService.save(liv);
		}
		else
			return null;
	}

	/* get all employees */
	@GetMapping("/affichall")
	public List<Livraison> getAllLivreur() {

		return livraisonService.findall();

	}

	@PutMapping("/modifier")

	public Livraison updateLiv(@RequestBody Livraison liv) {
		return livraisonService.updateLiv(liv);
	}

	@DeleteMapping("/delete/{id}")

	public void delete(@PathVariable(value = "id") long id) {
		livraisonService.delete(id);
	}

}
