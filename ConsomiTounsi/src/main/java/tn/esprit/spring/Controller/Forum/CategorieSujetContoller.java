package tn.esprit.spring.Controller.Forum;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.Forum.ICategorieSujetService;
import tn.esprit.spring.Model.Forum.CategorieSujet;
import tn.esprit.spring.Model.GestionProduit.Produit;


;

@RestController
@RequestMapping("/categorieSujet")
public class CategorieSujetContoller {
	@Autowired
	 ICategorieSujetService  icategorieSujetService;
	
	@PostMapping("/ajouter")
	public ResponseEntity AjouterCategorie( @Valid @RequestBody CategorieSujet c) {
		 icategorieSujetService.ajouterCategorieSujet(c);
			return ResponseEntity.created(null).build();
	}
	@GetMapping("/afficher")
	public List<CategorieSujet> AfficherCategorie() {
		return icategorieSujetService.getAllCategorieSujets();
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CategorieSujet> DeleteProduit(@PathVariable(value = "id") Long id) {
		CategorieSujet c = icategorieSujetService.findOne(id);
		if (c== null) {
			return ResponseEntity.notFound().build();
		}
		icategorieSujetService.deleteCategorieSujetById(id);
	return ResponseEntity.ok().build();
	}
	
	@GetMapping("/recherche/{nom}")
	public List<CategorieSujet> findLikeNameM(@PathVariable(value = "nom") String name) {
		return icategorieSujetService.findCategbyName(name);
	}
	@PutMapping(value = "/editnom/{id}/{nom}") 
	@ResponseBody
	public void mofierNomCateg(@PathVariable("nom") String nom, @PathVariable("id") long id) {
		icategorieSujetService.modifierNom(nom, id);
		
	}
}
