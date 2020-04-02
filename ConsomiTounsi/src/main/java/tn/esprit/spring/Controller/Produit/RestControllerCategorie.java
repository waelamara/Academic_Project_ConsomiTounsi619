package tn.esprit.spring.Controller.Produit;

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
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.Model.Produit.Categorie;
import tn.esprit.spring.Service.Produit.ICategorieService;

@RestController
@RequestMapping("/categorie")
public class RestControllerCategorie {
	@Autowired
	ICategorieService icategorieService;

	@PostMapping("/ajouter")
	public Categorie AjouterCategorie(@Valid @RequestBody Categorie c) {
		return icategorieService.save(c);
	}

	@GetMapping("/afficher")
	public List<Categorie> AfficherCategorie() {
		return icategorieService.findAll();
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> DeleteCategorie(@PathVariable(value = "id") Long idCategorie) {
		icategorieService.Delete(idCategorie);
		return ResponseEntity.ok("Categorie Supprimer avec succes");
	}

	@PutMapping("/edit/{id}")
	public Categorie EditProduit(@PathVariable(value = "id") Long idCategorie, @Valid @RequestBody Categorie c) {
		return icategorieService.Update(c, idCategorie);
	}
}
